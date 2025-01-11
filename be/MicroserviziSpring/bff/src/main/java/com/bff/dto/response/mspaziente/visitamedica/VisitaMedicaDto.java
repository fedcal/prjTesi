package com.bff.dto.response.mspaziente.visitamedica;

import com.bff.dto.response.mspaziente.relation.VisitaPrescrizioneDto;
import com.bff.dto.response.mspaziente.relation.VisitaSottoministrazioneInfermiereDto;
import com.bff.dto.response.mspaziente.relation.VisitaSottoministrazioneMedicoDto;
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
