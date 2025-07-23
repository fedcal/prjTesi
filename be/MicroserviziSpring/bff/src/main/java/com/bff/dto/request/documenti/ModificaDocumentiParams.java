package com.bff.dto.request.documenti;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "ModificaDocumentiParams")
public class ModificaDocumentiParams {
    @Schema(description = "Nome del file", type="string")
    private String nomeFile;

    @Schema(description = "Path del file", type="string")
    private String pathFile;

    @Schema(description = "Nuovo nome del file", type="string")
    private String nuovoNome;

    @Schema(description = "Nuovo path", type="string")
    private String nuovoPath;
}
