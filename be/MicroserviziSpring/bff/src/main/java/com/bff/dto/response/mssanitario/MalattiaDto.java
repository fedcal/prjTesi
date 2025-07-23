package com.bff.dto.response.mssanitario;

import com.bff.dto.response.mssanitario.relation.MalattiaCartellaDto;
import lombok.Data;

import java.util.Set;

@Data
public class MalattiaDto {
    private Integer idMalattia;
    private String nome;
    private String descrizione;
    private Set<MalattiaCartellaDto> cartelle;
}
