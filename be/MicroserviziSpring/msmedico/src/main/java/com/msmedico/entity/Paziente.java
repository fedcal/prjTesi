package com.msmedico.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "paziente")
public class Paziente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_paziente")
    private Long idPaziente;
}
