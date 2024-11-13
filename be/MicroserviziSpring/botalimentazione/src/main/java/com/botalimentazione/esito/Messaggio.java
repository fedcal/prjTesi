package com.botalimentazione.esito;

import com.botalimentazione.esito.constants.SeveritaMessaggioEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Messaggio {
    private SeveritaMessaggioEnum severita;
    private String codMsg;
    private List<Parametro> parametri;
}
