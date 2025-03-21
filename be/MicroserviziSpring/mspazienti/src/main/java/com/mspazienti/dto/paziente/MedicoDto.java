package com.mspazienti.dto.paziente;

import com.mspazienti.dto.paziente.relation.MedicinalePrescrizioneDto;
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
