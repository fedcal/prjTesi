package com.mspazienti.dto.paziente;

import lombok.Data;

import java.util.Set;

@Data
public class RepartoDto {
    private Long idReparto;
    private Long nomeReparto;
    private Long descrizioneReparto;
    private MedicoDto capoReparto;
    private Set<InfermiereDto> infermieri;
}