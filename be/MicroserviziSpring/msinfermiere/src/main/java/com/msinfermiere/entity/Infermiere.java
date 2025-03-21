package com.msinfermiere.entity;

import com.msinfermiere.entity.relation.MedicinaleSottoministrazione;
import com.msinfermiere.entity.relation.VisitaSottoministrazioneInfermiere;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "infermiere")
public class Infermiere {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_infermiere")
    private Integer idInfermiere;

    @Column(name = "nome_infermiere")
    private String nomeInfermiere;

    @Column(name = "cognome_infermiere")
    private String cognomeInfermiere;

    @Column(name = "turno_infermiere")
    private String turnoInfermiere;

    @ManyToOne
    @JoinColumn(name="id_reparto", nullable=false)
    private Reparto reparto;

    @OneToMany(mappedBy = "infermiere")
    private Set<MedicinaleSottoministrazione> medicinaleSottoministrazione;

    @OneToMany(mappedBy = "infermiere")
    private Set<VisitaSottoministrazioneInfermiere> visitaSottoministrazione;
}
