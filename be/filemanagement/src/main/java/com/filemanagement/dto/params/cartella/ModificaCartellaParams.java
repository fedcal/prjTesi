package com.filemanagement.dto.params.cartella;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "ModificaCartellaParams")
public class ModificaCartellaParams {

    @Schema(description = "Nome della cartella",type = "string")
    private String nuovoPath;

    @Schema(description = "Nome della cartella",type = "string")
    private String nuovoNomeCartella;

    @Schema(description = "Nome della cartella",type = "string")
    private String nomeCartella;

    @Schema(description = "Path delle cartella",type = "string")
    private String path;
}
