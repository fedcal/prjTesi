package com.chatbot.pal.esito;

import com.chatbot.pal.esito.constants.SeveritaMessaggioEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Messaggio {
    private SeveritaMessaggioEnum severita; //livello di criticita’ del messaggio da visualizzare (colorazione del Toaster FE); SeveritaMessaggioEnum
    private String codMsg;    //codice del messaggio che deve essere visualizzato, poi decodificato dal FE
    private List<Parametro> parametri;    //i valori da sostituire nella stringa del messaggio utente
}
