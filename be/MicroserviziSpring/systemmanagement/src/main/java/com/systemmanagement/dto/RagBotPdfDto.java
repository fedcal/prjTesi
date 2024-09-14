package com.systemmanagement.dto;

import lombok.Data;

@Data
public class RagBotPdfDto {
    private Long idBot;
    private String nomeBot;
    private String descrizioneBot;
    private String urlSpring;
    private String urlPython;
    private CartelleDto cartellaAddestramento;
    private CartelleDto cartellaCaricamentoMassivo;
}
