package com.msinfermiere.dto.infermiere;

import com.msinfermiere.dto.infermiere.relation.MedicinaleSottoministrazioneDto;
import com.msinfermiere.dto.infermiere.relation.VisitaSottoministrazioneInfermiereDto;
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
