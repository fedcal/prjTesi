package com.msmedico.dto.medico;

import com.msmedico.dto.medico.relation.MedicinaleSottoministrazioneDto;
import com.msmedico.dto.medico.relation.VisitaSottoministrazioneInfermiereDto;
import com.msmedico.entity.Reparto;
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
