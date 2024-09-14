# Tesi di laurea Federico Calò

Tesi di laurea in Sistemi ad Agenti di Federico Calò, matricola 678191, presso l'Università degli Studi di Bari "Aldo Moro". La stesura di questa tesi è stata realizzata in collaborazione con Links Management and Technology.

La tesi riguarda la creazione di chatbot addestrati su file PDF utilizzando la tecnica RAG (Retrieval-Augmented Generation). L'architettura utilizzata è basata su microservizi sviluppati attraverso il framework SpringBoot e 
la relativa dipendenza Spring AI. Inoltre, attraverso il framework ReactJs, viene fornita un'interfaccia grafica per la gestione dei file e per interaggire con i bot. 

## Generazione Key

Per generare la chiave di OpenApi per sfruttare i model di ChatGpt, accedere al sito https://platform.openai.com/api-keys e crearne una con tutti i permessi.

Per il database Astra DB, accedere al seguente link https://astra.datastax.com/c7c8ed45-f197-4c45-8220-7aed116279c3. Creare un database e generare un token.

## Stop and run OLLAMA ubuntu

ps aux | grep ollama
kill pid

systemctl start ollama.service

## Attivazione venv ubuntu
source venv/bin/activate