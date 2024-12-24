package com.msinfermiere.entity;

import com.msinfermiere.entity.relation.MalattiaCartella;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity(name = "malattia")
@Table(name = "malattia")
@Data
public class Malattia {
    @Id
    @Column(name="id_malattia")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idMalattia;

    @Column(name="nome")
    private String nome;

    @Column(name="descrizione")
    private String descrizione;

    @OneToMany(mappedBy = "malattia")
    private Set<MalattiaCartella> cartelle;
}
