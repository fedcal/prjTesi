package com.msmedico.dto.medico;

import lombok.Data;

@Data
public class PazienteDto {
    private Long idPaziente;
    private String nome;
    private String cognome;
    private String dataNascita;
    private String luogoNascita;
    private String provinciaNascita;
    private ContattoRiferimentoDto contattoRiferimento;
    private RepartoDto reparto;
    private CartellaClinicaDto cartellaClinica;
}
