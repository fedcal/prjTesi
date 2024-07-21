package com.filemanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "documenti")
@Getter
@Setter
public class Documenti {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_documento", columnDefinition = "INT")
    private Long idDocumento;

    @Column(name = "nome_documento", columnDefinition = "VARCHAR(500)",unique=true)
    private String nomeDocumento;

    @Column(name = "estensione_documento", columnDefinition = "VARCHAR(5)",unique=true)
    private String estensioneDocumento;

    @Column(name = "path", columnDefinition = "VARCHAR(8000)",unique=true)
    private String path;
}
