import React from 'react'
import './Spiegazione.css'
import { Link } from 'react-router-dom'

function Spiegazione() {
  return (
    <>
      <section className='spiegazione-section'>
        <h1>Tesi</h1>
        <p>
          Il progetto nasce dall'idea di creare diversi chatbot addestrati mediante tecnica RAG in diversi ambiti di interesse a livello aziendale, i quali andranno ad interagire con l'utente mediante l'utilizzo di una Web Application.
          Dopo una fase iniziale di anali, si cè creata un'architettura basata su servizi Rest Api sviluppati in Spring Boot per far comunicare il Front End sviluppato in ReactJs con i vari bot sviluppati e addestrati utilizzando Python.
          <br/><br/>
          L'architettura così creata ha il vantaggio di rendere flessibile l'applicazione dei chatbot addestrati attraverso la tecnica Rag in vari contesti applicativi, mantenendo uno strato di sicurezza solido attraverso l'utilizzo del 
          framework Spring Boot e un'interfaccia user friendly che può essere sviluppata mediante ReactJs.

          <br/><br/>

          Si rimanda alla lettura della <Link to="https://www.federicocalo.dev">tesi</Link> per maggiori informazioni.

        </p>
        
        <h1>Realizzazione</h1>
        <p>Attraverso il programma Ollama si scarica localmente l'LLM che verrà addestrato attraverso la metodologia RAG sui file pdf, grazie ancyhe all'utilizzo del framework LangChain per la veottorizzazione dei documenti e la 
          creazione del VectorStore. Successivamente si creano i vari endpoint Rest in Flask per far comunicare il bot con il corrispettivo microservizio implementato in SpringBoot. Oltre a questi microservizi che rappresentano i 
          vari chatbot, si ha a disposizione un microservizio per la gestione dei documenti sui quali i vari chatbot verranno addestrati e un layer denominato BFF per far comunicare il frontend con il backend. Infine abbiamo il frontend in 
          ReactJs per far interagire l'utente con il sistema. </p>
       
        <h1>Come funziona il sistema</h1>
        <p>L'interfaccia della web app è composta principalmente da due sezioni:</p>
        <ul>
          <li>la sezione <span>CHAT</span></li>
          <li>la sezione <span>GESTIONE BOT</span></li>
        </ul>

        <p>
          Nella sezione chat si può selezionare il chatbot tra quelli registrati e addestrati, per poi avviare una chat con esso. Mentre nella sezione 'Gestione Bot' si possono gestire i vari bot, ovvero si può registrare un nuovo bot,
          caricare i file su cui bisogna addestrare il bot e avviare l'addestramento del bot.
        </p>
       
      </section> 
    </>
  )
}

export default Spiegazione