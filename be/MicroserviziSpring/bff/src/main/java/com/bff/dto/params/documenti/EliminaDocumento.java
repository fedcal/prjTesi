package com.bff.dto.params.documenti;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "EliminaDocumento")
public class EliminaDocumento {
    @Schema(description = "Nome del file", type="string")
    private String nomeFile;

    @Schema(description = "Path file", type="string")
    private String pathFile;
}
