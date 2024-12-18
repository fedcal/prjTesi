package com.mspazienti.dto.paziente;

import lombok.Data;

@Data
public class InfermiereDto {
    private Long idInfermiere;
    private String nomeInfermiere;
    private String cognomeInfermiere;
    private String turnoInfermiere;
    private RepartoDto reparto;
}
