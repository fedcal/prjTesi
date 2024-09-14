package com.bff.esito;

import com.bff.esito.constants.EsitoOperazioneEnum;
import lombok.Data;

import java.util.List;

@Data
public class Esito {
    private EsitoOperazioneEnum codRet;
    private List<Messaggio> messaggi;
    private String operationId;
}
