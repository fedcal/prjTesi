from flask import Flask, request
from langchain_community.vectorstores import Chroma
from langchain_community.llms import Ollama
from langchain_text_splitters import RecursiveCharacterTextSplitter
from langchain_community.embeddings.fastembed import FastEmbedEmbeddings
from langchain_community.document_loaders import PDFPlumberLoader
from langchain.chains.combine_documents import create_stuff_documents_chain
from langchain.chains import create_retrieval_chain
from langchain.prompts import PromptTemplate
from mysql.connector import connect, Error
import os
import logging
from logging.handlers import RotatingFileHandler

from langdetect import detect
from deep_translator import GoogleTranslator
import re

pathAddestramento = ""
connectionDB = None

logging.basicConfig(filename='app.log', level=logging.DEBUG,
                    format='%(asctime)s %(levelname)s: %(message)s')

logger = logging.getLogger(__name__)

handler = RotatingFileHandler("app.log", maxBytes=1024*1024*1024, backupCount=3)
handler.setLevel(logging.DEBUG)

formatter = logging.Formatter('%(asctime)s %(levelname)s: %(message)s')
handler.setFormatter(formatter)

logger.addHandler(handler)

app = Flask(__name__)

llm = Ollama(model="llama3") #8b
#llm = Ollama(model="llama3:70b") #70b
#llm = Ollama(model="gemma2") #9b
#llm = Ollama(model="mistral-large") #123b

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

@app.route('/message', methods=['POST'])
def botAiMessage():
    jsonContent = request.json
    query = jsonContent.get('query')
    print(f"Query: {query}")

    response = llm.invoke(query)
    responseAnswer = {"message": response}

    return responseAnswer


@app.route('/load-pdf', methods=['POST'])
def loadPdf():
    file = request.files['file']
    fileName = file.filename
    saveFile = ""
    if os.name == 'nt':
        saveFile = pathAddestramento + "\\" + fileName
    else:
        saveFile = pathAddestramento + "/" + fileName
    file.save(saveFile)
    print(f"File salvato: {saveFile}")

    if not fileName[-4:] == ".pdf":
        response = {
            "status": "success",
            "fileName": fileName,
            "docLen": 0,
            "chunks": 0
        }
        return response

    loadPdf = PDFPlumberLoader(saveFile)

    docs = loadPdf.load_and_split()
    print(f"Doc len: {len(docs)}")

    chunks = text_splitter.split_documents(docs)
    print(f"Doc len: {len(chunks)}")

    response = {
        "status": "success",
        "fileName": fileName,
        "docLen": len(docs),
        "chunks": len(chunks)
    }
    if(len(docs)==0 or len(chunks)==0 ):
        response = {
            "status": "failed",
            "fileName": fileName,
            "docLen": len(docs),
            "chunks": len(chunks)
        }
    else:
        vectorStore = Chroma.from_documents(documents=chunks, embedding=embedding, persist_directory=pathAddestramento)
        vectorStore.persist()
    return response


@app.route('/message-pdf', methods=['POST'])
def askPdf():
    jsonContent = request.json
    query = jsonContent.get('query')
    print(f"Query: {query}")

    print(f"Carico il VectorStore")
    vectorStore = Chroma(persist_directory=pathAddestramento,
                         embedding_function=embedding)

    print(f"Creo la chain")
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

    print(result)

    sources = []
    for doc in result["context"]:
        sources.append(
            {"source": doc.metadata["source"], "pageContent": doc.page_content}
        )

    responseAnswer = {"answer": check_and_translate(result["answer"]), "sources": sources}

    return responseAnswer


def connessioneDb():
    global connectionDB
    try:
        connectionDB = connect(
            host="localhost",
            user="root",
            password="root",
            database="botrag",
        )
        app.logger.info('Connessione db.')
    except Error as e:
        app.logger.info(e)


def recuperoPathAddestramento():
    global connectionDB
    global pathAddestramento
    query = "SELECT path_cartella, nome_cartella FROM cartelle AS cart LEFT JOIN rag_bot_pdf AS ragbot ON cart.id_cartella = ragbot.id_cartella_addestramento WHERE nome_bot = 'botOfferteBandi' AND cart.is_cartella_addestramento = 1"
    try:
        with connectionDB.cursor() as cursor:
            cursor.execute(query)
            for folder in cursor.fetchall():
                app.logger.info(folder)
                for field in folder:
                    if pathAddestramento == "":
                        pathAddestramento = field
                    else:
                        if os.name == 'nt':
                            pathAddestramento = pathAddestramento + "\\" + field
                        else:
                            pathAddestramento = pathAddestramento + "/" + field
    except Error as e:
        app.logger.info(e)
    finally:
        connectionDB.close()


def startApplication():
    connessioneDb()
    recuperoPathAddestramento()
    app.run(host='127.0.0.1', port=5001, debug=True)

# Funzione per verificare se il testo contiene parole in inglese
def contains_english_words(text):
    english_words = r'\b[a-zA-Z]+\b'
    return re.search(english_words, text) is not None


# Funzione per rilevare la lingua e tradurre se necessario
def check_and_translate(text):
    # Rileva la lingua del testo
    detected_language = detect(text)
    print(f"Lingua rilevata: {detected_language}")

    # Se la lingua è inglese, traduci in italiano
    if detected_language == 'en':
        translated_text = GoogleTranslator(source='en', target='it').translate(text)
        return translated_text
    # Se il testo è in italiano e contiene parole inglesi, restituisci il testo originale
    elif detected_language == 'it' and contains_english_words(text):
        print("Il testo contiene parole in inglese, non viene tradotto.")
        return text
    # Se il testo è in italiano senza parole inglesi, può essere restituito direttamente
    elif detected_language == 'it':
        return text

if __name__ == '__main__':
    startApplication()