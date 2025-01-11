package com.bff.dto.request.msinfermiere;

import lombok.Data;

@Data
public class FilterInfermiereDto {
    private Integer idInfermiere;
    private String nomeInfermiere;
    private String cognomeInfermiere;
    private Integer idReparto;
    private String turnoInfermiere;
}
