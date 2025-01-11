package com.bff.dto.request.documenti;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FindDocumentoParams {
    @Schema(description = "Path delle cartella",type = "string")
    private String titoloDocumento;

    @Schema(description = "Path delle cartella",type = "string")
    private String pathDocumento;
}
