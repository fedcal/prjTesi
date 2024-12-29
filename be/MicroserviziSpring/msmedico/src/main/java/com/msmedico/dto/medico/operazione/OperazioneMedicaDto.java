package com.msmedico.dto.medico.operazione;

import com.msmedico.dto.medico.relation.OperazioneCartellaDto;
import com.msmedico.dto.medico.relation.OperazionePrescrizioneDto;
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
