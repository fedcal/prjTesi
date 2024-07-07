package com.chatbot.pal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Documenti")
@Getter
@Setter
public class Documenti {
    @Id
    @Column(name = "idDocumento", columnDefinition = "INT")
    private Long idDocumento;

    @Column(name = "nomeDocumento", columnDefinition = "VARCHAR(500)",unique=true)
    private String nomeDocumento;

    @Column(name = "estensioneDocumento", columnDefinition = "VARCHAR(5)",unique=true)
    private String estensioneDocumento;

    @Column(name = "path", columnDefinition = "VARCHAR(8000)",unique=true)
    private String path;
}
