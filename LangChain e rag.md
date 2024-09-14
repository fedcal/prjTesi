Langchain è un framework open source che permette agli sviluppatori di combinare gli LLms con risorse esterne di computazione e dati. L'obiettivo è quello di suddividere ogni documento in chunks che verranno storicizzati in un Vectore Database. Quindi ogni documento ha una propria rappresentazione vettoriale del testo.

La domanda che posta dall'utente viene inviata all'LLM e la relativa vettorizzazione viene utilizzata per effettuare una ricerca di similarità all'interno del vector store. Lo scopo della ricerca è quello di ottenere i chuncks rilevanti dal vector store e condividerli con l'LLM. 

In questa fase l'LLM ha le informazioni rilevanti dal vectore store e la domanda iniziale posta dall'utente. In base a queste informazioni può generare una risposta o effettuare un'azione.