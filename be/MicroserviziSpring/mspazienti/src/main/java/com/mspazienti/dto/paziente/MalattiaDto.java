package com.mspazienti.dto.paziente;

import com.mspazienti.dto.paziente.relation.MalattiaCartellaDto;
import lombok.Data;

import java.util.Set;

@Data
public class MalattiaDto {
    private Integer idMalattia;
    private String nome;
    private String descrizione;
    private Set<MalattiaCartellaDto> cartelle;
}
