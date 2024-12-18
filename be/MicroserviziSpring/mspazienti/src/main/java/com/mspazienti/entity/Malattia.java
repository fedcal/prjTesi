package com.mspazienti.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "malattia")
@Data
public class Malattia {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_medicinale")
    private Long idMedicinale;

    @Column(name = "nome")
    private Long nomeMedicinale;

    @Column(name = "descrizione")
    private Long descrizioneMedicinale;
}
