package com.systemmanagement.dto.params.ragbotpdf;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RegistraBotParams {
    @Schema(description = "Path delle cartella",type = "string")
    private String nomeBot;

    @Schema(description = "Path delle cartella",type = "string")
    private String descrizioneBot;

    @Schema(description = "Path delle cartella",type = "string")
    private String urlSpring;

    @Schema(description = "Path delle cartella",type = "string")
    private String urlPython;

    @Schema(description = "Path delle cartella",type = "string")
    private String nomeCartellaAddestramento;

    @Schema(description = "Path delle cartella",type = "string")
    private String nomeCartellaCaricamentoMassivo;
}
