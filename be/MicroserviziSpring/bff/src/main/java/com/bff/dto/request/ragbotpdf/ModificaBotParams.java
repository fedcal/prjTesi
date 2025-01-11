package com.bff.dto.request.ragbotpdf;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ModificaBotParams {
    @Schema(description = "Path delle cartella",type = "string")
    private String nomeBot;

    @Schema(description = "Path delle cartella",type = "string")
    private String nuovoNomeBot;

    @Schema(description = "Path delle cartella",type = "string")
    private String nuovaDescrizioneBot;

    @Schema(description = "Path delle cartella",type = "string")
    private String nuovoUrlSpring;

    @Schema(description = "Path delle cartella",type = "string")
    private String nuovoUrlPython;

    @Schema(description = "Path delle cartella",type = "string")
    private String nuovoNomeCartellaAddestramento;

    @Schema(description = "Path delle cartella",type = "string")
    private String nuovoNomeCartellaCaricamentoMassivo;
}
