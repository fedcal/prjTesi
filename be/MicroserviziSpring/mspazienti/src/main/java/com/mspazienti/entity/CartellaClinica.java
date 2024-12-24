package com.mspazienti.entity;

import com.mspazienti.entity.relation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "cartella_clinica")
public class CartellaClinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cartella_clinica")
    private Integer idCartellaClinica;

    @Column(name = "gruppo_sanguigno")
    private String gruppoSanguigno;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy="cartellaClinica")
    private Set<Diagnosi> diagnosi;

    @OneToMany(mappedBy = "cartellaClinica")
    private Set<MalattiaCartella> malattiaCartella;

    @OneToMany(mappedBy = "cartellaClinica")
    private Set<VisitaMedicaCartella> visitaMedicaCartella;

    @OneToMany(mappedBy = "cartellaClinica")
    private Set<OperazioneCartella> operazioneCartella;

    @OneToMany(mappedBy = "cartellaClinica")
    private Set<MedicinalePrescrizione> medicinalePrescrizione;

    @OneToMany(mappedBy = "cartellaClinica")
    private Set<VisitaPrescrizione> visitaPrescrizione;

    @OneToMany(mappedBy = "cartellaClinica")
    private Set<VisitaSottoministrazioneMedico> visitaSottoministrazioneMedico;

    @OneToMany(mappedBy = "cartellaClinica")
    private Set<VisitaSottoministrazioneInfermiere> visitaSottoministrazioneInfermiere;

    @OneToMany(mappedBy = "cartellaClinica")
    private Set<MedicinaleCartella> medicinaleCartella;

    @OneToMany(mappedBy = "cartellaClinica")
    private Set<OperazionePrescrizione> operazionePrescrizione;

    @OneToMany(mappedBy = "cartellaClinica")
    private Set<MedicinaleSottoministrazione> medicinaleSottoministrazione;
}
