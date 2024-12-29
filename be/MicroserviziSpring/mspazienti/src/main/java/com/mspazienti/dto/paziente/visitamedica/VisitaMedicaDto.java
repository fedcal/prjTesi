package com.mspazienti.dto.paziente.visitamedica;

import com.mspazienti.dto.paziente.relation.VisitaPrescrizioneDto;
import com.mspazienti.dto.paziente.relation.VisitaSottoministrazioneInfermiereDto;
import com.mspazienti.dto.paziente.relation.VisitaSottoministrazioneMedicoDto;
import lombok.Data;

import java.util.Set;

@Data
public class VisitaMedicaDto {
    private Integer idVisitaMedica;
    private String nome;
    private String tipologia;
    private String descrizione;
    private Set<VisitaPrescrizioneDto> visitaPrescrizione;
    private Set<VisitaSottoministrazioneMedicoDto> visitaSottoministrazioneMedico;
    private Set<VisitaSottoministrazioneInfermiereDto> visitaSottoministrazioneInfermiere;
}
