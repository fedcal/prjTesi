package com.bff.dto.response.mssanitario.operazione;

import com.bff.dto.response.mssanitario.relation.OperazioneCartellaDto;
import com.bff.dto.response.mssanitario.relation.OperazionePrescrizioneDto;
import lombok.Data;

import java.util.Set;

@Data
public class OperazioneMedicaDto {
    private Integer idOperazioneMedica;
    private String nome;
    private String descrizione;
    private String tipologia;
    private Set<OperazioneCartellaDto> operazioneCartella;
    private Set<OperazionePrescrizioneDto> operazionePrescrizione;
}
