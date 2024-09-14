package com.systemmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "cartelle")
@Getter
@Setter
public class Cartelle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_cartella")
    private Long idCartella;

    @Column(name = "nome_cartella", columnDefinition = "VARCHAR(500)", unique = true)
    private String nomeCartella;

    @Column(name = "path_cartella", columnDefinition = "VARCHAR(5000)")
    private String path;

    @Column(name = "is_cartella_addestramento", columnDefinition = "BOOLEAN")
    private Boolean isCartellaAddestramento;

    @OneToMany(mappedBy="cartella")
    private Set<Documenti> documenti;

    @OneToOne(mappedBy = "cartellaAddestramento")
    private RagBotPdf cartellaAddestramento;

    @OneToOne(mappedBy = "cartellaCaricamentoMassivo")
    private RagBotPdf cartellaCaricamentoMassivo;
}
