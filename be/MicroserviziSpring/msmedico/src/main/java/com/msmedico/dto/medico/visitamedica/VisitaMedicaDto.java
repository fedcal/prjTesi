package com.msmedico.dto.medico.visitamedica;

import com.msmedico.dto.medico.relation.VisitaPrescrizioneDto;
import com.msmedico.dto.medico.relation.VisitaSottoministrazioneInfermiereDto;
import com.msmedico.dto.medico.relation.VisitaSottoministrazioneMedicoDto;
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
