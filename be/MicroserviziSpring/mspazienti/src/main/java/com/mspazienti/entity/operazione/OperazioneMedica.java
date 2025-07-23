package com.mspazienti.entity.operazione;

import com.mspazienti.entity.relation.OperazioneCartella;
import com.mspazienti.entity.relation.OperazionePrescrizione;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Table(name = "operazione_medica")
@Entity
public class OperazioneMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operazione_medica")
    private Integer idOperazioneMedica;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "tipologia")
    private String tipologia;

    @OneToMany(mappedBy = "operazioneMedica")
    private Set<OperazioneCartella> operazioneCartella;

    @OneToMany(mappedBy = "operazioneMedica")
    private Set<OperazionePrescrizione> operazionePrescrizione;
}
