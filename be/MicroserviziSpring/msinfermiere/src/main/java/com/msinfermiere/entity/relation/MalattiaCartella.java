package com.msinfermiere.entity.relation;

import com.msinfermiere.entity.CartellaClinica;
import com.msinfermiere.entity.Malattia;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "malattia_cartella")
public class MalattiaCartella {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relazione")
    private Integer idRelazione;

    @ManyToOne
    @JoinColumn(name = "id_malattia",referencedColumnName="id_malattia")
    private Malattia malattia;

    @ManyToOne
    @JoinColumn(name = "id_cartella",referencedColumnName="id_cartella_clinica")
    private CartellaClinica cartellaClinica;
}
