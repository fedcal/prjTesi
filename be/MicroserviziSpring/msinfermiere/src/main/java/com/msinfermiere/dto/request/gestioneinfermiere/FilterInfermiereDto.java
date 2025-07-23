package com.msinfermiere.dto.request.gestioneinfermiere;

import lombok.Data;

@Data
public class FilterInfermiereDto {
    private Integer idInfermiere;
    private String nomeInfermiere;
    private String cognomeInfermiere;
    private Integer idReparto;
    private String turnoInfermiere;
}
