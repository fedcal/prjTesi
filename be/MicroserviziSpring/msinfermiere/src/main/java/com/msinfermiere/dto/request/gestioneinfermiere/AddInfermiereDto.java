package com.msinfermiere.dto.request.gestioneinfermiere;

import lombok.Data;

@Data
public class AddInfermiereDto {
    private String nomeInfermiere;
    private String cognomeInfermiere;
    private String turnoInfermiere;
    private Integer idReparto;
}
