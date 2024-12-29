package com.mspazienti.entity;

import com.mspazienti.entity.relation.MedicinalePrescrizione;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_medico")
    private Integer idMedico;

    @Column(name = "nome_medico")
    private String nomeMedico;

    @Column(name = "cognome_medico")
    private String cognomeMedico;

    @Column(name = "turno_medico")
    private String turnoMedico;

    @OneToOne(mappedBy = "capoReparto")
    private Reparto reparto;

    @OneToMany(mappedBy = "medico")
    private Set<MedicinalePrescrizione> medicinalePrescrizioneSet;
}
