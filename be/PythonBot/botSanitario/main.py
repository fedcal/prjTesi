import os
import logging
from logging.handlers import RotatingFileHandler
import re

from fastapi import FastAPI, Request, File, UploadFile
from fastapi.responses import JSONResponse
from dotenv import load_dotenv
from mysql.connector import connect, Error

from langchain_community.vectorstores import Chroma
from langchain_community.llms import Ollama
from langchain_together import ChatTogether
from langchain_text_splitters import RecursiveCharacterTextSplitter
from langchain_community.embeddings.fastembed import FastEmbedEmbeddings
from langchain_community.document_loaders import PDFPlumberLoader
from langchain.chains.combine_documents import create_stuff_documents_chain
from langchain.chains import create_retrieval_chain
from langchain.prompts import PromptTemplate
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from langdetect import detect
from deep_translator import GoogleTranslator
from contextlib import asynccontextmanager

load_dotenv()

DB_HOST = os.getenv("DB_HOST")
DB_USER = os.getenv("DB_USER")
DB_PASSWORD = os.getenv("DB_PASSWORD")
DB_NAME = os.getenv("DB_NAME")

pathAddestramento = ""
connectionDB = None

# Logger setup
logger = logging.getLogger("uvicorn.error")
handler = RotatingFileHandler("app.log", maxBytes=1024*1024*1024, backupCount=3)
formatter = logging.Formatter('%(asctime)s %(levelname)s: %(message)s')
handler.setFormatter(formatter)
logger.addHandler(handler)
logger.setLevel(logging.DEBUG)

app = FastAPI()

# LLM setup con fallback
try:
    together_llm = ChatTogether(model="meta-llama/Llama-4-Scout-17B-16E-Instruct")
    llm = Ollama(model="llama3").with_fallbacks([together_llm])
except Exception as e:
    logger.warning(f"Errore TogetherAI: {e} - provo Ollama locale")
    try:
        llm = Ollama(model="llama3")
    except Exception as e:
        logger.error(f"Errore Ollama locale: {e}")
        llm = None

embedding = FastEmbedEmbeddings()

text_splitter = RecursiveCharacterTextSplitter(
    chunk_size=3796,
    chunk_overlap=100,
    length_function=len,
    separators=[r"\n\n", r"\n", r"(?<=\.\s)", " ", ""]
)

rawPrompt = PromptTemplate.from_template(""" 
    <s>[INST] Sei un assistente esperto nella ricerca di informazioni nei documenti PDF. Le tue risposte devono essere precise e basate esclusivamente sul contesto fornito. Assicurati di rispondere in italiano e di non fare supposizioni. Indica chiaramente la fonte da cui hai estratto l'informazione se disponibile. [/INST]</s>

    [INST] {input}
            Context: {context}
            Risposta in italiano: 
    [/INST]
""")


@app.post("/message")
async def bot_sanitario_message(data: dict):
    query = data.get("query")
    logger.info(f"Query: {query}")
    if not llm:
        return JSONResponse(status_code=503, content={"error": "LLM non disponibile"})
    response = llm.invoke(query)
    return {"query": query, "message": check_and_translate(response)}


@app.post("/evaluete-message")
async def bot_sanitario_evaluate_message(data: dict):
    query = data.get("query")
    logger.info(f"Query: {query}")

    logger.info(f"Carico il VectorStore")
    vectorStore = Chroma(persist_directory=pathAddestramento,
                         embedding_function=embedding)

    logger.info(f"Creo la chain")
    retriever = vectorStore.as_retriever(
        search_type="similarity_score_threshold",
        search_kwargs={
            "k": 20,
            "score_threshold": 0.3,
        },
    )

    document_chain = create_stuff_documents_chain(llm, rawPrompt)
    chain = create_retrieval_chain(retriever, document_chain)

    result = chain.invoke({"input": f"Rispondi in italiano: {query}"})

    logger.debug(f"Result: {result}")

    sources = []
    for doc in result["context"]:
        sources.append(
            {"source": doc.metadata["source"], "pageContent": doc.page_content}
        )

    rispostaCorretta = """Ecco la mia risposta:
    **Schema alimentare 1 (8C)**
    ...
    (inserisci qui la risposta corretta completa)
    """

    similarity_score = calculate_similarity(
        check_and_translate(result["answer"]).replace("\n", "").replace("\t", "").replace("+", "").replace("*", ""),
        rispostaCorretta.replace("\n", "").replace("\t", "").replace("+", "").replace("*", "")
    )

    logger.info(f"Similarità: {similarity_score}")
    logger.info(f"Answer: {check_and_translate(result['answer'])}")

    return {
        "answer": check_and_translate(result["answer"]),
        "sources": sources,
        "query": query,
        "similarity": similarity_score
    }


@app.post("/load-pdf")
async def load_pdf(file: UploadFile = File(...)):
    fileName = file.filename
    if os.name == 'nt':
        saveFile = os.path.join(pathAddestramento, fileName)
    else:
        saveFile = os.path.join(pathAddestramento, fileName)

    with open(saveFile, "wb") as f:
        content = await file.read()
        f.write(content)

    logger.info(f"File salvato: {saveFile}")

    if not fileName.lower().endswith(".pdf"):
        return {
            "status": "failed",
            "fileName": fileName,
            "docLen": 0,
            "chunks": 0
        }

    loader = PDFPlumberLoader(saveFile)
    docs = loader.load_and_split()
    logger.info(f"Doc len: {len(docs)}")

    chunks = text_splitter.split_documents(docs)
    logger.info(f"Chunks len: {len(chunks)}")

    if len(docs) == 0 or len(chunks) == 0:
        return {
            "status": "failed",
            "fileName": fileName,
            "docLen": len(docs),
            "chunks": len(chunks)
        }

    vectorStore = Chroma.from_documents(documents=chunks, embedding=embedding, persist_directory=pathAddestramento)
    vectorStore.persist()

    return {
        "status": "success",
        "fileName": fileName,
        "docLen": len(docs),
        "chunks": len(chunks)
    }


@app.post("/message-pdf")
async def ask_pdf(data: dict):
    query = data.get("query")
    logger.info(f"Query: {query}")

    logger.info(f"Carico il VectorStore")
    vectorStore = Chroma(persist_directory=pathAddestramento,
                         embedding_function=embedding)

    logger.info(f"Creo la chain")
    retriever = vectorStore.as_retriever(
        search_type="similarity_score_threshold",
        search_kwargs={
            "k": 20,
            "score_threshold": 0.3,
        },
    )

    document_chain = create_stuff_documents_chain(llm, rawPrompt)
    chain = create_retrieval_chain(retriever, document_chain)

    result = chain.invoke({"input": f"Rispondi in italiano: {query}"})

    logger.debug(f"Result: {result}")

    sources = []
    for doc in result["context"]:
        sources.append(
            {"source": doc.metadata["source"], "pageContent": doc.page_content}
        )

    return {
        "answer": check_and_translate(result["answer"]),
        "sources": sources,
        "query": query
    }


def connessioneDb():
    global connectionDB
    try:
        connectionDB = connect(
            host=DB_HOST,
            user=DB_USER,
            password=DB_PASSWORD,
            database=DB_NAME,
        )
        logger.info('Connessione db riuscita.')
    except Error as e:
        logger.error(f"Errore connessione DB: {e}")


def recuperoPathAddestramento():
    global connectionDB
    global pathAddestramento
    query = """
    SELECT path_cartella, nome_cartella 
    FROM cartelle AS cart 
    LEFT JOIN rag_bot_pdf AS ragbot ON cart.id_cartella = ragbot.id_cartella_addestramento 
    WHERE nome_bot = 'botSanitario' AND cart.is_cartella_addestramento = 1
    """
    if not connectionDB:
        logger.error("Nessuna connessione al DB disponibile")
        return

    try:
        with connectionDB.cursor() as cursor:
            cursor.execute(query)
            for folder in cursor.fetchall():
                logger.info(f"Folder DB: {folder}")
                for field in folder:
                    if pathAddestramento == "":
                        pathAddestramento = field
                    else:
                        if os.name == 'nt':
                            pathAddestramento = pathAddestramento + "\\" + field
                        else:
                            pathAddestramento = pathAddestramento + "/" + field
    except Error as e:
        logger.error(f"Errore query DB: {e}")
    finally:
        connectionDB.close()


def contains_english_words(text):
    english_words = r'\b[a-zA-Z]+\b'
    return re.search(english_words, text) is not None


def check_and_translate(text):
    detected_language = detect(text)
    logger.info(f"Lingua rilevata: {detected_language}")

    if detected_language == 'en':
        translated_text = GoogleTranslator(source='en', target='it').translate(text)
        return translated_text
    elif detected_language == 'it' and contains_english_words(text):
        logger.info("Il testo contiene parole in inglese, non viene tradotto.")
        return text
    elif detected_language == 'it':
        return text
    return text


def calculate_similarity(text1, text2):
    vectorizer = TfidfVectorizer()
    tfidf_matrix = vectorizer.fit_transform([text1, text2])
    similarity = cosine_similarity(tfidf_matrix[0:1], tfidf_matrix[1:2])
    return similarity[0][0]

@asynccontextmanager
async def lifespan(app: FastAPI):
    connessioneDb()
    recuperoPathAddestramento()

