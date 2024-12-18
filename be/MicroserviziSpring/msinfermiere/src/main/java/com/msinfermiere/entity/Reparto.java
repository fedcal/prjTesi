package com.msinfermiere.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "reparto")
public class Reparto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_reparto")
    private Long idReparto;

    @Column(name = "nome_reparto")
    private Long nomeReparto;

    @Column(name = "descrizione_reparto")
    private Long descrizioneReparto;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "capo_reparto", referencedColumnName = "id_medico")
    private Medico capoReparto;

    @OneToMany(mappedBy="reparto")
    private Set<Infermiere> infermieri;
}
