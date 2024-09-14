package com.systemmanagement.dto.params.ragbotpdf;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AddestramentoMassivoFile {

    @Schema(description = "Nome della cartella",type = "string")
    private String nomeCartella;

    @Schema(description = "Path delle cartella",type = "string")
    private String path;
}
