package com.msmedico.dto.medico;

import com.msmedico.entity.Infermiere;
import com.msmedico.entity.Medico;
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
