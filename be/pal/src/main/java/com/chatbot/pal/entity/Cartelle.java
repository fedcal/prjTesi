package com.chatbot.pal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Cartelle")
@Getter
@Setter
public class Cartelle {
    @Id
    @Column(name = "idCartella", columnDefinition = "INT")
    private Long idCartella;

    @Column(name = "nomeCartella", columnDefinition = "VARCHAR(500)",unique=true)
    private String nomeCartella;

    @Column(name = "path", columnDefinition = "VARCHAR(8000)",unique=true)
    private String path;
}
