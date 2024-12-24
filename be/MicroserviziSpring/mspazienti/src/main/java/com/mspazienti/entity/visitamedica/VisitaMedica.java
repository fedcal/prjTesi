package com.mspazienti.entity.visitamedica;

import com.mspazienti.entity.relation.VisitaPrescrizione;
import com.mspazienti.entity.relation.VisitaSottoministrazioneInfermiere;
import com.mspazienti.entity.relation.VisitaSottoministrazioneMedico;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "visita_medica")
public class VisitaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_visita_medica")
    private Integer idVisitaMedica;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipologia")
    private String tipologia;

    @Column(name = "descrizione")
    private String descrizione;

    @OneToMany(mappedBy = "visitaMedica")
    private Set<VisitaPrescrizione> visitaPrescrizione;

    @OneToMany(mappedBy = "visitaMedica")
    private Set<VisitaSottoministrazioneMedico> visitaSottoministrazioneMedico;

    @OneToMany(mappedBy = "visitaMedica")
    private Set<VisitaSottoministrazioneInfermiere> visitaSottoministrazioneInfermiere;
}
