package com.mspazienti.entity.relation;

import com.mspazienti.entity.CartellaClinica;
import com.mspazienti.entity.visitamedica.RefertoVisitaMedica;
import com.mspazienti.entity.visitamedica.VisitaMedica;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "visita_medica_cartella")
public class VisitaMedicaCartella {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relazione")
    private Integer idRelazione;

    @ManyToOne
    @JoinColumn(name = "id_cartella",referencedColumnName="id_cartella_clinica")
    private CartellaClinica cartellaClinica;

    @ManyToOne
    @JoinColumn(name = "id_visita_medica",referencedColumnName="id_visita_medica")
    private VisitaMedica visitaMedica;

    @OneToOne
    @JoinColumn(name = "id_referto", referencedColumnName = "id_referto")
    private RefertoVisitaMedica refertoVisitaMedica;
}
