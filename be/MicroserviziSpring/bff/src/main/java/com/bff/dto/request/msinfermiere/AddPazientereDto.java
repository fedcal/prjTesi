package com.bff.dto.request.msinfermiere;

import lombok.Data;

@Data
public class AddPazientereDto {
    private String nome;
    private String cognome;
    private String dataNascita;
    private String luogoNascita;
    private Integer idReparto;
    private Integer idCartellaClinica;
    private Integer idContattoRiferimento;
}
