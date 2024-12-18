package com.msmedico.dto.medico;

import com.msmedico.entity.Reparto;
import lombok.Data;

@Data
public class MedicoDto {
    private Long idMedico;
    private Long nomeMedico;
    private Long cognomeMedico;
    private String turnoMedico;
    private RepartoDto reparto;
}
