package com.filemanagement.dto.params;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartellaParams {
    @NotNull(message = "Il nome della cartella non può essere vuoto")
    @NotEmpty(message = "Il nome della cartella non può essere vuoto")
    @Schema(description = "Nome della cartella",type = "string")
    private String nomeCartella;

    @NotNull(message = "Il nome della cartella non può essere vuoto")
    @NotEmpty(message = "Il nome della cartella non può essere vuoto")
    @Schema(description = "Path delle cartella",type = "string")
    private String path;
}
