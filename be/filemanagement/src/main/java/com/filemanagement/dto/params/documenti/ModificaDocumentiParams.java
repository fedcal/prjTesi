package com.filemanagement.dto.params.documenti;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "ModificaDocumentiParams")
public class ModificaDocumentiParams {
    @Schema(description = "Nome della cartella", type="string")
    private String nomeFile;
    @Schema(description = "Nome della cartella", type="string")
    private String pathFile;
    @Schema(description = "Nome della cartella", type="string")
    private String nuovoNome;
    @Schema(description = "Nome della cartella", type="string")
    private String nuovoPath;
}
