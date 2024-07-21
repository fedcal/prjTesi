package com.filemanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cartelle")
@Getter
@Setter
public class Cartelle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cartella", columnDefinition = "INT")
    private Long idCartella;

    @Column(name = "nome_cartella", columnDefinition = "VARCHAR(500)",unique=true)
    private String nomeCartella;

    @Column(name = "path", columnDefinition = "VARCHAR(8000)",unique=true)
    private String path;
}
