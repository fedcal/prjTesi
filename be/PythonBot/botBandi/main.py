from flask import Flask, request
from langchain_community.vectorstores import Chroma
from langchain_community.llms import Ollama
from langchain_text_splitters import RecursiveCharacterTextSplitter
from langchain_community.embeddings.fastembed import FastEmbedEmbeddings
from langchain_community.document_loaders import PDFPlumberLoader
from langchain.chains.combine_documents import create_stuff_documents_chain
from langchain.chains import create_retrieval_chain
from langchain.prompts import PromptTemplate

pathFolder = "D:\\prj\\prjTesi\\be\\PythonBot\\botBandi\\FileAddestramentoBandi"

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


def startApplication():
    app.run(host='127.0.0.1', port=5001, debug=True)


if __name__ == '__main__':
    startApplication()