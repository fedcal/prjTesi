package com.systemmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "documenti")
@Getter
@Setter
public class Documenti {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "documenti_seq")
    @SequenceGenerator(name = "documenti_seq", sequenceName = "documenti_seq", allocationSize = 500)
    @Column(name = "id_documento")
    private Long idDocumento;

    @Column(name = "nome_documento", columnDefinition = "VARCHAR(500)")
    private String nomeDocumento;

    @Column(name = "estensione_documento", columnDefinition = "VARCHAR(8)")
    private String estensioneDocumento;

    @Column(name = "path_documento", columnDefinition = "VARCHAR(5000)")
    private String path;

    @ManyToOne
    @JoinColumn(name="id_cartella")
    private Cartelle cartella;
}
