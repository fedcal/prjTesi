package com.chatbot.pal.esito;

import com.chatbot.pal.esito.constants.EsitoOperazioneEnum;
import lombok.Data;

import java.util.List;

@Data
public class Esito {
    private EsitoOperazioneEnum codRet;    //l’esito dell’operazione; EsitoOperazioneEnum
    private String operationId;    //codice univoco che identifica l’operazione
    private List<Messaggio> messaggi;    //l’array dove si trovano tutti i messaggi da visualizzare all’utente

}
