package com.filemanagement.dto.params.documenti;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "EliminaDocumento")
public class EliminaDocumento {
    @Schema(description = "Nome della cartella", type="string")
    private String nomeFile;
    @Schema(description = "Nome della cartella", type="string")
    private String pathFile;
}
