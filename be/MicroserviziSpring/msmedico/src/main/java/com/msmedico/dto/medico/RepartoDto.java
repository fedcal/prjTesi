package com.msmedico.dto.medico;

import com.msmedico.entity.Infermiere;
import com.msmedico.entity.Medico;
import lombok.Data;

import java.util.Set;

@Data
public class RepartoDto {
    private Integer idReparto;
    private String nomeReparto;
    private String descrizioneReparto;
    private MedicoDto capoReparto;
    private Set<InfermiereDto> infermieri;
}
