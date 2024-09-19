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

llm = Ollama(model="llama3")
embedding = FastEmbedEmbeddings()
text_splitter = RecursiveCharacterTextSplitter(
    chunk_size=1024,
    chunk_overlap=20,
    length_function=len,
    is_separator_regex=False
)

rawPrompt = PromptTemplate.from_template(""" 
    <s>[INST] Sei un assistente abile nella ricerca di informazioni all''interno dei documenti. Se non hai una 
    risposta non fare nulla. [/INST]</s>

    [INST] {input}
            Context: {context}
            Answer: 
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
    saveFile = pathFolder + "\\" + fileName
    file.save(saveFile)
    print(f"File salvato: {saveFile}")

    loadPdf = PDFPlumberLoader(saveFile)
    docs = loadPdf.load_and_split()

    print(f"Doc len: {len(docs)}")

    chunks = text_splitter.split_documents(docs)
    print(f"Doc len: {len(chunks)}")

    vectorStore = Chroma.from_documents(documents=chunks,
                                        embedding=embedding,
                                        persist_directory=pathFolder)
    vectorStore.persist()

    response = {
        "status": "success",
        "fileName": fileName,
        "docLen": len(docs),
        "chunks": len(chunks)
    }
    return response


@app.route('/message-pdf', methods=['POST'])
def askPdf():
    jsonContent = request.json
    query = jsonContent.get('query')
    print(f"Query: {query}")

    print(f"Carico il VectorStore")
    vectorStore = Chroma(persist_directory=pathFolder,
                         embedding_function=embedding)

    print(f"Creo la chain")
    retriever = vectorStore.as_retriever(
        search_type="similarity_score_threshold",
        search_kwargs={
            "k": 20,
            "score_threshold": 0.1,
        },
    )

    document_chain = create_stuff_documents_chain(llm, rawPrompt)
    chain = create_retrieval_chain(retriever, document_chain)

    result = chain.invoke({"input": query})

    print(result)

    sources = []
    for doc in result["context"]:
        sources.append(
            {"source": doc.metadata["source"], "pageContent": doc.page_content}
        )

    responseAnswer = {"answer": result["answer"], "sources": sources}

    return responseAnswer

def connessioneDb():
    global connectionDB
    try:
            connectionDB = connect(
                host="localhost",
                user="root",
                password="root",
                database = "botrag",
            )
            app.logger.info('Connessione db.')
    except Error as e:
        app.logger.info(e)

def recuperoPathAddestramento():
    global connectionDB
    global pathAddestramento
    query = "SELECT path_cartella, nome_cartella FROM cartelle AS cart LEFT JOIN rag_bot_pdf AS ragbot ON cart.id_cartella = ragbot.id_cartella_addestramento WHERE nome_bot = 'botAi' AND cart.is_cartella_addestramento = 1"
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

def startApplication():
    connessioneDb()
    recuperoPathAddestramento()
    app.run(host='127.0.0.1', port=5001, debug=True)


if __name__ == '__main__':
    startApplication()