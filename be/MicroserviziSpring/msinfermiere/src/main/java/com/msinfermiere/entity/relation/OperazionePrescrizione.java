package com.msinfermiere.entity.relation;

import com.msinfermiere.entity.CartellaClinica;
import com.msinfermiere.entity.Medico;
import com.msinfermiere.entity.operazione.OperazioneMedica;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="operazione_prescrizione")
public class OperazionePrescrizione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relazione")
    private Integer idRelazione;

    @ManyToOne
    @JoinColumn(name = "id_operazione",referencedColumnName = "id_operazione_medica")
    private OperazioneMedica operazioneMedica;

    @ManyToOne
    @JoinColumn(name = "id_cartella",referencedColumnName = "id_cartella_clinica")
    private CartellaClinica cartellaClinica;

    @ManyToOne
    @JoinColumn(name = "id_medico",referencedColumnName = "id_medico")
    private Medico medico;
}
