package com.bff.dto.response.mspaziente;

import com.bff.dto.response.mspaziente.relation.MedicinaleSottoministrazioneDto;
import com.bff.dto.response.mspaziente.relation.VisitaSottoministrazioneInfermiereDto;
import lombok.Data;

import java.util.Set;

@Data
public class InfermiereDto {
    private Integer idInfermiere;
    private String nomeInfermiere;
    private String cognomeInfermiere;
    private String turnoInfermiere;
    private RepartoDto reparto;
    private Set<MedicinaleSottoministrazioneDto> medicinaleSottoministrazione;
    private Set<VisitaSottoministrazioneInfermiereDto> visitaSottoministrazione;
}
