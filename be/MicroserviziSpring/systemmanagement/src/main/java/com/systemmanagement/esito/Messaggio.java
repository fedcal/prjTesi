package com.systemmanagement.esito;

import com.systemmanagement.esito.constants.SeveritaMessaggioEnum;
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