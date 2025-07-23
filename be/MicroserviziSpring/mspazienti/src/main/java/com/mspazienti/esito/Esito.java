package com.mspazienti.esito;

import com.mspazienti.esito.constants.EsitoOperazioneEnum;
import lombok.Data;

import java.util.List;

@Data
public class Esito {
    private EsitoOperazioneEnum codRet;
    private String operationId;
    private List<Messaggio> messaggi;
}
