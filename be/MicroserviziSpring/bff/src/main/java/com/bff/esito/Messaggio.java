package com.bff.esito;

import com.bff.esito.constants.SeveritaMessaggioEnum;
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
