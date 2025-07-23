package com.msinfermiere.entity.relation;

import com.msinfermiere.entity.CartellaClinica;
import com.msinfermiere.entity.Medico;
import com.msinfermiere.entity.visitamedica.VisitaMedica;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "visita_prescrizione")
public class VisitaPrescrizione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relazione")
    private Integer idRelazione;

    @ManyToOne
    @JoinColumn(name = "id_medico",referencedColumnName="id_medico")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "id_visita",referencedColumnName="id_visita_medica")
    private VisitaMedica visitaMedica;

    @ManyToOne
    @JoinColumn(name = "id_cartella_clinica",referencedColumnName="id_cartella_clinica")
    private CartellaClinica cartellaClinica;
}
