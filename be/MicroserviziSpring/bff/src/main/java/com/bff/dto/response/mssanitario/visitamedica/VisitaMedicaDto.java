package com.bff.dto.response.mssanitario.visitamedica;

import com.bff.dto.response.mssanitario.relation.VisitaPrescrizioneDto;
import com.bff.dto.response.mssanitario.relation.VisitaSottoministrazioneInfermiereDto;
import com.bff.dto.response.mssanitario.relation.VisitaSottoministrazioneMedicoDto;
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
