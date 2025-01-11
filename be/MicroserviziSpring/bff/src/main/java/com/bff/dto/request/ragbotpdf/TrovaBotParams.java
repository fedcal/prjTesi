package com.bff.dto.request.ragbotpdf;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TrovaBotParams {
    @Schema(description = "Nome del bot",type = "string")
    private String nomeBot;
    @Schema(description = "Id del bot",type = "int")
    private int idBot;
}
