package com.msinfermiere.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "medicinale")
@Data
public class Medicinale {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_medicinale")
    private Integer idMedicinale;

    @Column(name = "nome_medicinale")
    private String nomeMedicinale;

    @Column(name = "descrizione_medicinale")
    private String descrizioneMedicinale;
}
