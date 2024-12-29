package com.msinfermiere.dto.infermiere.visitamedica;

import com.msinfermiere.dto.infermiere.relation.VisitaPrescrizioneDto;
import com.msinfermiere.dto.infermiere.relation.VisitaSottoministrazioneInfermiereDto;
import com.msinfermiere.dto.infermiere.relation.VisitaSottoministrazioneMedicoDto;
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
