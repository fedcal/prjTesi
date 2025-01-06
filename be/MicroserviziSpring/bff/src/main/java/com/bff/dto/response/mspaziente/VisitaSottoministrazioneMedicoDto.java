package com.bff.dto.response.mspaziente;

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
