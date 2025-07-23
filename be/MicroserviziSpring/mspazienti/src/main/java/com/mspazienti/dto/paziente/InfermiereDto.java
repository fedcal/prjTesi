package com.mspazienti.dto.paziente;

import com.mspazienti.dto.paziente.relation.MedicinaleSottoministrazioneDto;
import com.mspazienti.dto.paziente.relation.VisitaSottoministrazioneInfermiereDto;
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
