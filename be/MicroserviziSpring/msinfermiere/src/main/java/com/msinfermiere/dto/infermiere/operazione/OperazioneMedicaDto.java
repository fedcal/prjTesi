package com.msinfermiere.dto.infermiere.operazione;

import com.msinfermiere.dto.infermiere.relation.OperazioneCartellaDto;
import com.msinfermiere.dto.infermiere.relation.OperazionePrescrizioneDto;
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
