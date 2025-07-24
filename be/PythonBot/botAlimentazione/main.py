import os
import logging
import re
import shutil
from contextlib import asynccontextmanager

from fastapi import FastAPI, File, UploadFile
from fastapi.responses import JSONResponse
from dotenv import load_dotenv
from mysql.connector import connect, Error

from langchain_community.vectorstores import Chroma
from langchain_community.embeddings.fastembed import FastEmbedEmbeddings
from langchain_community.llms import Ollama
from langchain_together import ChatTogether
from langchain.chains.combine_documents import create_stuff_documents_chain
from langchain.chains import create_retrieval_chain
from langchain.prompts import PromptTemplate
from langchain_text_splitters import RecursiveCharacterTextSplitter
from langchain_community.document_loaders import PDFPlumberLoader

from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from langdetect import detect
from deep_translator import GoogleTranslator

load_dotenv()

app = FastAPI()

DB_HOST = os.getenv("DB_HOST")
DB_USER = os.getenv("DB_USER")
DB_PASSWORD = os.getenv("DB_PASSWORD")
DB_NAME = os.getenv("DB_NAME")

connectionDB = None
pathAddestramento = ""

logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

try:
    together_llm = ChatTogether(model="meta-llama/Llama-4-Scout-17B-16E-Instruct")
    llm = Ollama(model="llama3").with_fallbacks([together_llm])
except Exception as e:
    print(f"Errore con TogetherAI: {e}")
    try:
        llm = Ollama(model="llama3")
    except Exception as e:
        print(f"Ollama non disponibile: {e}")
        llm = None

embedding = FastEmbedEmbeddings()

text_splitter = RecursiveCharacterTextSplitter(
    chunk_size=3796,
    chunk_overlap=100,
    length_function=len,
    separators=[r"\n\n", r"\n", r"(?<=\.\s)", " ", ""]
)

prompt = PromptTemplate.from_template("""
    <s>[INST] Sei un assistente esperto nella ricerca di informazioni nei documenti PDF. Le tue risposte devono essere precise e basate esclusivamente sul contesto fornito. Assicurati di rispondere in italiano e di non fare supposizioni. Indica chiaramente la fonte da cui hai estratto l'informazione se disponibile. [/INST]</s>
    [INST] {input}
    Context: {context}
    Risposta in italiano:
    [/INST]
""")


@app.post("/message")
async def message(data: dict):
    query = data.get("query")
    utente_id = 0  # In futuro sarà dinamico

    if not llm:
        return JSONResponse(status_code=503, content={"error": "LLM non disponibile"})

    # Recupera memoria conversazionale recente (ultimi 5 messaggi)
    cronologia = recupera_memoria_conversazionale(utente_id, n=5)
    contesto = "\n".join([f"Utente: {d}\nBot: {r}" for d, r in reversed(cronologia)])

    # Costruisci il prompt con la cronologia
    prompt_contestuale = f"{contesto}\nUtente: {query}\nBot:"

    # Genera la risposta
    response = llm.invoke(prompt_contestuale)
    risposta = check_and_translate(response)

    # Salva nuova conversazione
    salva_conversazione(utente_id, query, risposta)

    return {"query": query, "message": risposta}


@app.post("/evaluate-message")
async def evaluate_message(data: dict):
    query = data.get("query")
    utente_id = 0  # Da rendere dinamico in futuro

    # 1. Recupero memoria da MySQL
    cronologia = recupera_memoria_conversazionale(utente_id, n=5)
    contesto_memoria = "\n".join([f"Utente: {q}\nBot: {a}" for q, a in reversed(cronologia)])

    # 2. Retrieval da Chroma DB
    vectorStore = Chroma(persist_directory=pathAddestramento, embedding_function=embedding)
    retriever = vectorStore.as_retriever(
        search_type="similarity_score_threshold",
        search_kwargs={"k": 20, "score_threshold": 0.3}
    )

    document_chain = create_stuff_documents_chain(llm, prompt)
    chain = create_retrieval_chain(retriever, document_chain)
    result = chain.invoke({"input": f"Rispondi in italiano: {query}"})

    # 3. Risposta LLM basata su memoria + documenti
    risposta_documentale = result["answer"]
    risposta = check_and_translate(risposta_documentale)

    # 4. Salva la conversazione in MySQL
    salva_conversazione(utente_id, query, risposta)

    # 5. Fonti documentali
    sources = [
        {"source": doc.metadata.get("source", ""), "pageContent": doc.page_content}
        for doc in result.get("context", [])
    ]

    # 6. Similarità con una risposta attesa (mockata qui)
    rispostaCorretta = "Ecco la mia risposta:\n\n..."
    similarity_score = calculate_similarity(risposta, rispostaCorretta)

    return {
        "answer": risposta,
        "sources": sources,
        "query": query,
        "similarity": similarity_score
    }


@app.post("/load-pdf")
async def load_pdf(file: UploadFile = File(...)):
    global pathAddestramento
    file_path = os.path.join(pathAddestramento, file.filename)
    with open(file_path, "wb") as f:
        shutil.copyfileobj(file.file, f)

    if not file.filename.endswith(".pdf"):
        return {"status": "failed", "fileName": file.filename, "docLen": 0, "chunks": 0}

    loader = PDFPlumberLoader(file_path)
    docs = loader.load_and_split()
    chunks = text_splitter.split_documents(docs)

    if not docs or not chunks:
        return {"status": "failed", "fileName": file.filename, "docLen": len(docs), "chunks": len(chunks)}

    vectorStore = Chroma.from_documents(documents=chunks, embedding=embedding, persist_directory=pathAddestramento)
    vectorStore.persist()

    return {"status": "success", "fileName": file.filename, "docLen": len(docs), "chunks": len(chunks)}


@app.post("/message-pdf")
async def ask_pdf(data: dict):
    query = data.get("query")
    utente_id = 0  # Provvisorio, in futuro gestire utente reale

    # 1. Recupero memoria utente da MySQL
    cronologia = recupera_memoria_conversazionale(utente_id, n=5)
    contesto_memoria = "\n".join([f"Utente: {q}\nBot: {a}" for q, a in reversed(cronologia)])

    # 2. Retrieval semantico con Chroma
    vectorStore = Chroma(persist_directory=pathAddestramento, embedding_function=embedding)
    retriever = vectorStore.as_retriever(
        search_type="similarity_score_threshold",
        search_kwargs={"k": 20, "score_threshold": 0.3}
    )

    # 3. Prompt combinato: memoria + domanda
    input_finale = f"""Usa il seguente contesto della conversazione con l'utente, se utile, per rispondere in modo coerente.

Cronologia recente:
{contesto_memoria}

Domanda attuale:
{query}
"""

    document_chain = create_stuff_documents_chain(llm, prompt)
    chain = create_retrieval_chain(retriever, document_chain)
    result = chain.invoke({"input": input_finale})

    # 4. Traduzione, salvataggio, fonti
    risposta = check_and_translate(result["answer"])
    salva_conversazione(utente_id, query, risposta)

    sources = [
        {"source": doc.metadata.get("source", ""), "pageContent": doc.page_content}
        for doc in result.get("context", [])
    ]

    return {
        "answer": risposta,
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
        logger.info("Connessione DB riuscita.")
    except Error as e:
        logger.warning(f"Errore DB: {e}")


def recuperoPathAddestramento():
    global pathAddestramento, connectionDB
    if not connectionDB:
        return
    query = """
    SELECT path_cartella, nome_cartella
    FROM cartelle AS cart
    LEFT JOIN rag_bot_pdf AS ragbot ON cart.id_cartella = ragbot.id_cartella_addestramento
    WHERE nome_bot = 'botAlimentazione' AND cart.is_cartella_addestramento = 1
    """
    try:
        with connectionDB.cursor() as cursor:
            cursor.execute(query)
            for folder in cursor.fetchall():
                for field in folder:
                    if pathAddestramento == "":
                        pathAddestramento = field
                    else:
                        pathAddestramento = os.path.join(pathAddestramento, field)
    except Error as e:
        logger.error(f"Errore query DB: {e}")
    finally:
        connectionDB.close()


def calculate_similarity(text1, text2):
    vectorizer = TfidfVectorizer()
    tfidf_matrix = vectorizer.fit_transform([text1, text2])
    similarity = cosine_similarity(tfidf_matrix[0:1], tfidf_matrix[1:2])
    return similarity[0][0]


def contains_english_words(text):
    return re.search(r'\b[a-zA-Z]+\b', text) is not None


def check_and_translate(text):
    detected_language = detect(text)
    if detected_language == 'en':
        return GoogleTranslator(source='en', target='it').translate(text)
    elif detected_language == 'it' and contains_english_words(text):
        return text
    return text


def salva_conversazione(utente_id, domanda, risposta):
    try:
        with connectionDB.cursor() as cursor:
            query = """
            INSERT INTO conversazioni (utente_id, domanda, risposta)
            VALUES (%s, %s, %s)
            """
            cursor.execute(query, (utente_id, domanda, risposta))
        connectionDB.commit()
    except Error as e:
        logger.error(f"Errore salvataggio conversazione: {e}")

def recupera_memoria_conversazionale(utente_id, n=5):
    try:
        with connectionDB.cursor() as cursor:
            query = """
            SELECT domanda, risposta
            FROM conversazioni
            WHERE utente_id = %s
            ORDER BY id DESC
            LIMIT %s
            """
            cursor.execute(query, (utente_id, n))
            return cursor.fetchall()
    except Error as e:
        logger.error(f"Errore recupero memoria: {e}")
        return []


@asynccontextmanager
def startup_event():
    connessioneDb()
    recuperoPathAddestramento()