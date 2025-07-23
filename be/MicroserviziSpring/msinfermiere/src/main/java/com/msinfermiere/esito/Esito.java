package com.msinfermiere.esito;

import com.msinfermiere.esito.constants.EsitoOperazioneEnum;
import lombok.Data;

import java.util.List;

@Data
public class Esito {
    private EsitoOperazioneEnum codRet;
    private String operationId;
    private List<Messaggio> messaggi;
}
