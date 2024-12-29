package com.msinfermiere.dto.infermiere;

import com.msinfermiere.dto.infermiere.relation.MedicinalePrescrizioneDto;
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
