package com.mspazienti.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
