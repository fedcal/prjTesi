package com.systemmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rag_bot_pdf")
@Getter
@Setter
public class RagBotPdf {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_rag_bot_pdf")
    private Long idBot;

    @Column(name = "nome_bot", unique = true)
    private String nomeBot;

    @Column(name = "descrizione_bot", columnDefinition = "VARCHAR(500)")
    private String descrizioneBot;

    @Column(name = "url_spring", columnDefinition = "VARCHAR(500)", unique = true)
    private String urlSpring;

    @Column(name = "url_python", columnDefinition = "VARCHAR(500)", unique = true)
    private String urlPython;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cartella_addestramento", referencedColumnName = "id_cartella", nullable=false, columnDefinition = "INT")
    private Cartelle cartellaAddestramento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cartella_caricamento_massivo", referencedColumnName = "id_cartella", nullable=false, columnDefinition = "INT")
    private Cartelle cartellaCaricamentoMassivo;
}
