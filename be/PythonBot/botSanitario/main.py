from flask import Flask, request
from langchain_community.vectorstores import Chroma
from langchain_community.llms import Ollama
from langchain_together import ChatTogether
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
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

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

# TogetherAI requires an API key to be set via the TOGETHER_API_KEY environment variable
# A free trial API key can be obtained here: https://api.together.ai/
together_llm = ChatTogether(model="meta-llama/Llama-4-Scout-17B-16E-Instruct")

# Use Ollama by default and fallback to TogetherAI when Ollama is not available
llm = Ollama(model="llama3").with_fallbacks([together_llm])

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
def botAlimentazioneMessage():
    jsonContent = request.json
    query = jsonContent.get('query')
    print(f"Query: {query}")

    response = llm.invoke(query)
    responseAnswer = {"query": query, "message": check_and_translate(response)}

    return responseAnswer

@app.route('/evaluete-message', methods=['POST'])
def botAlimentazioneEvalueteMessage():
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

    rispostaCorretta = "Ecco la mia risposta:\n\n**Schema alimentare 1 (8C)**\n\n* Pranzo:\n\t+ Pasta di semola secca: scegli tra pasta di semola secca 100g preferibilmente integrale, pasta di semola fresca 140g o pasta all'uovo fresca 120g\n\t+ Condimento del primo piatto: sugo semplice al pomodoro, sugo all'arrabbiata, in brodo (vegetale), condimento di verdure a piacere, in bianco, con aglio olio e peperoncino. È possibile aggiungere 1 cucchiaino di formaggio grattugiato stagionato (grana, parmigiano, pecorino) se gradito.\n* Colazione:\n\t+ Opzione a scelta: una tazza di latte parzialmente scremato da 250ml, due vasetti di yogurt naturale magro non zuccherato da 125g o un bicchiere di kefir da 300ml. In alternativa, può scegliere una tazza di latte da 125 ml + uno yogurt naturale magro da 125g\n\t+ In aggiunta: 6 biscotti frollini o 7 biscotti secchi preferibilmente integrali (60g), 5 fette biscottate preferibilmente integrali (60g) con un velo sottile di marmellata, due panini piccoli preferibilmente integrali (100g) con un velo sottile di marmellata o 8-10 cucchiai di cereali per la colazione preferibilmente integrali, o cornflakes (60g)\n* Possibilità di utilizzare una bevanda calda come caffè, orzo o tè non zuccherato in bustina (non superare i 2 cucchiaini di zucchero o miele al giorno per zuccherare le bevande calde)\n\n**Schema alimentare 1 (8C)**\n\n* Pranzo:\n\t+ Pasta di semola secca: scegli tra pasta di semola secca 100g preferibilmente integrale, pasta di semola fresca 140g o pasta all'uovo fresca 120g\n\t+ Condimento del primo piatto: sugo semplice al pomodoro, sugo all'arrabbiata, in brodo (vegetale), condimento di verdure a piacere, in bianco, con aglio olio e peperoncino. È possibile aggiungere 1 cucchiaino di formaggio grattugiato stagionato (grana, parmigiano, pecorino) se gradito.\n* Colazione:\n\t+ Opzione a scelta: una tazza di latte parzialmente scremato da 250ml, due vasetti di yogurt naturale magro non zuccherato da 125g o un bicchiere di kefir da 300ml. In alternativa, può scegliere una tazza di latte da 125 ml + uno yogurt naturale magro da 125g\n\t+ In aggiunta: 6 biscotti frollini o 7 biscotti secchi preferibilmente integrali (60g), 5 fette biscottate preferibilmente integrali (60g) con un velo sottile di marmellata, due panini piccoli preferibilmente integrali (100g) con un velo sottile di marmellata o 8-10 cucchiai di cereali per la colazione preferibilmente integrali, o cornflakes (60g)\n* Possibilità di utilizzare una bevanda calda come caffè, orzo o tè non zuccherato in bustina (non superare i 2 cucchiaini di zucchero o miele al giorno per zuccherare le bevande calde)\n\n**Schema alimentare 1 (8C)**\n\n* Pranzo:\n\t+ Pasta di semola secca: scegli tra pasta di semola secca 100g preferibilmente integrale, pasta di semola fresca 140g o pasta all'uovo fresca 120g\n\t+ Condimento del primo piatto: sugo semplice al pomodoro, sugo all'arrabbiata, in brodo (vegetale), condimento di verdure a piacere, in bianco, con aglio olio e peperoncino. È possibile aggiungere 1 cucchiaino di formaggio grattugiato stagionato (grana, parmigiano, pecorino) se gradito.\n* Colazione:\n\t+ Opzione a scelta: una tazza di latte parzialmente scremato da 250ml, due vasetti di yogurt naturale magro non zuccherato da 125g o un bicchiere di kefir da 300ml. In alternativa, può scegliere una tazza di latte da 125 ml + uno yogurt naturale magro da 125g\n\t+ In aggiunta: 6 biscotti frollini o 7 biscotti secchi preferibilmente integrali (60g), 5 fette biscottate preferibilmente integrali (60g) con un velo sottile di marmellata, due panini piccoli preferibilmente integrali (100g) con un velo sottile di marmellata o 8-10 cucchiai di cereali per la colazione preferibilmente integrali, o cornflakes (60g)\n* Possibilità di utilizzare una bevanda calda come caffè, orzo o tè non zuccherato in bustina (non superare i 2 cucchiaini di zucchero o miele al giorno per zuccherare le bevande calde)"
    # Calcola la similarità tra la risposta generata e la risposta di riferimento
    similarity_score = calculate_similarity(
        check_and_translate(result["answer"]).replace("\n", "").replace("\t", "").replace("+", "").replace("*", ""),  # Risposta generata
        rispostaCorretta.replace("\n", "").replace("\t", "").replace("+", "").replace("*", "")  # Risposta di riferimento
    )

    # Stampa del risultato
    print(f"Similarità: {similarity_score}")

    print(f"answer: {check_and_translate(result["answer"])}")

    responseAnswer = {"answer": check_and_translate(result["answer"]), "sources": sources, "query": query, "similarity": similarity_score}

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

    responseAnswer = {"answer": check_and_translate(result["answer"]), "sources": sources, "query": query}

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
    query = "SELECT path_cartella, nome_cartella FROM cartelle AS cart LEFT JOIN rag_bot_pdf AS ragbot ON cart.id_cartella = ragbot.id_cartella_addestramento WHERE nome_bot = 'botSanitario' AND cart.is_cartella_addestramento = 1"
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
    app.run(host='127.0.0.1', port=5003, debug=True)

def contains_english_words(text):
    english_words = r'\b[a-zA-Z]+\b'
    return re.search(english_words, text) is not None


def check_and_translate(text):
    detected_language = detect(text)
    print(f"Lingua rilevata: {detected_language}")

    if detected_language == 'en':
        translated_text = GoogleTranslator(source='en', target='it').translate(text)
        return translated_text
    elif detected_language == 'it' and contains_english_words(text):
        print("Il testo contiene parole in inglese, non viene tradotto.")
        return text
    elif detected_language == 'it':
        return text

def calculate_similarity(text1, text2):
    """
    Calcola la similarità del coseno tra due testi.
    """
    vectorizer = TfidfVectorizer()
    tfidf_matrix = vectorizer.fit_transform([text1, text2])
    similarity = cosine_similarity(tfidf_matrix[0:1], tfidf_matrix[1:2])
    return similarity[0][0]

if __name__ == '__main__':
    startApplication()