package com.msinfermiere.entity.relation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "infermiere_paziente")
@Getter
@Setter
public class InfermierePazienteRel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_infermiere")
    private Long idInfermiere;
}
