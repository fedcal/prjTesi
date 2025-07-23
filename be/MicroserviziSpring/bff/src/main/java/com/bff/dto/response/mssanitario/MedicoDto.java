package com.bff.dto.response.mssanitario;

import com.bff.dto.response.mssanitario.relation.MedicinalePrescrizioneDto;
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
