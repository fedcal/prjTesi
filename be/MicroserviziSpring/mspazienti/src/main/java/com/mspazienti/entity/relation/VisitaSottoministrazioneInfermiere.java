package com.mspazienti.entity.relation;

import com.mspazienti.entity.CartellaClinica;
import com.mspazienti.entity.Infermiere;
import com.mspazienti.entity.visitamedica.VisitaMedica;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "visita_sottoministrazione_infermiere")
public class VisitaSottoministrazioneInfermiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relazione")
    private Integer idRelazione;

    @ManyToOne
    @JoinColumn(name = "id_infermiere",referencedColumnName="id_infermiere")
    private Infermiere infermiere;

    @ManyToOne
    @JoinColumn(name = "id_visita",referencedColumnName="id_visita_medica")
    private VisitaMedica visitaMedica;

    @ManyToOne
    @JoinColumn(name = "id_cartella_clinica",referencedColumnName="id_cartella_clinica")
    private CartellaClinica cartellaClinica;
}