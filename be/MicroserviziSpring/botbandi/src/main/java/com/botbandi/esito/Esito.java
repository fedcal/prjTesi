package com.botbandi.esito;

import com.botbandi.esito.constants.EsitoOperazioneEnum;
import lombok.Data;

import java.util.List;

@Data
public class Esito {
    private EsitoOperazioneEnum codRet;
    private String operationId;
    private List<Messaggio> messaggi;
}
