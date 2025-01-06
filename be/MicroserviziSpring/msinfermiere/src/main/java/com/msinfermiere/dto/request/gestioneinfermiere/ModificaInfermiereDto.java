package com.msinfermiere.dto.request.gestioneinfermiere;

import lombok.Data;

@Data
public class ModificaInfermiereDto {
    private Integer idInfermiere;
    private String nuovoNomeInfermiere;
    private String nuovoCognomeInfermiere;
    private Integer nuovoIdReparto;
    private String nuovoTurnoInfermiere;
}
