package com.bff.dto.response.mspaziente.operazione;

import com.bff.dto.response.mspaziente.relation.OperazioneCartellaDto;
import com.bff.dto.response.mspaziente.relation.OperazionePrescrizioneDto;
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
