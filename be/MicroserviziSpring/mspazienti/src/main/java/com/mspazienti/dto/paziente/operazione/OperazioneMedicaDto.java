package com.mspazienti.dto.paziente.operazione;

import com.mspazienti.dto.paziente.relation.OperazioneCartellaDto;
import com.mspazienti.dto.paziente.relation.OperazionePrescrizioneDto;
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
