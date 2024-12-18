package com.mspazienti.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_medico")
    private Long idMedico;

    @Column(name = "nome_medico")
    private Long nomeMedico;

    @Column(name = "cognome_medico")
    private Long cognomeMedico;

    @Column(name = "turno_medico", columnDefinition = "VARCHAR(100)")
    private String turnoMedico;

    @OneToOne(mappedBy = "capoReparto")
    private Reparto reparto;
}
