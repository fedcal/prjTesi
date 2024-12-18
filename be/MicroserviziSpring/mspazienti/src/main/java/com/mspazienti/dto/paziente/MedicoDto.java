package com.mspazienti.dto.paziente;

import lombok.Data;

@Data
public class MedicoDto {
    private Long idMedico;
    private Long nomeMedico;
    private Long cognomeMedico;
    private String turnoMedico;
    private RepartoDto reparto;
}
