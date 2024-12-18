package com.msinfermiere.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "infermiere")
public class Infermiere {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_infermiere")
    private Long idInfermiere;

    @Column(name = "nome_infermiere", columnDefinition = "VARCHAR(100)")
    private String nomeInfermiere;

    @Column(name = "cognome_infermiere", columnDefinition = "VARCHAR(100)")
    private String cognomeInfermiere;

    @Column(name = "turno_infermiere", columnDefinition = "VARCHAR(100)")
    private String turnoInfermiere;

    @ManyToOne
    @JoinColumn(name="id_reparto", nullable=false)
    private Reparto reparto;
}
