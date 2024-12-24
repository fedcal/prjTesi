package com.msinfermiere.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "contatto_riferimento")
public class ContattoRiferimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contatto")
    private Integer idContatto;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "numero_cellulare")
    private String numeroCellulare;
}
