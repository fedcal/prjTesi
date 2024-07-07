package com.bff.esito;

import com.bff.esito.constants.EsitoOperazioneEnum;
import lombok.Data;

import java.util.List;

@Data
public class Esito {
    private EsitoOperazioneEnum codRet;    //l’esito dell’operazione; EsitoOperazioneEnum
    private List<Messaggio> messaggi;    //l’array dove si trovano tutti i messaggi da visualizzare all’utente
    private String operationId;    //codice univoco che identifica l’operazione
}
