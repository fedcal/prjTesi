package com.msmedico.dto.medico;

import com.msmedico.dto.medico.relation.MedicinalePrescrizioneDto;
import com.msmedico.entity.Reparto;
import lombok.Data;

import java.util.Set;

@Data
public class MedicoDto {
    private Integer idMedico;
    private String nomeMedico;
    private String cognomeMedico;
    private String turnoMedico;
    private RepartoDto reparto;
    private Set<MedicinalePrescrizioneDto> medicinalePrescrizioneSet;
}
