package com.filemanagement.controller;

import com.filemanagement.dto.params.CartellaParams;
import com.filemanagement.dto.params.ModificaCartellaParams;
import com.filemanagement.esito.EsitoMessaggiRequestContextHolder;
import com.filemanagement.esito.GenericResponseDto;
import com.filemanagement.service.CartelleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartelle")
@Validated
public class CartelleController {

    @Autowired
    private EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;

    @Autowired
    private CartelleService cartelleService;

    @Operation(summary = "Creazione della cartella",
            description = "Creazione della cartella dove storicizzare i file e aggiornamento db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PutMapping(value = "/crea", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<GenericResponseDto<String>> aggiungiCartella(@Valid @ParameterObject CartellaParams cartellaParams) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(cartelleService.aggiungiCartella(cartellaParams)));
    }

    @Operation(summary = "Rinominare una cartella cartella",
            description = "Rinominare una cartella cartella e aggiornare i path dei file contenuti in essa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/rinomina", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<GenericResponseDto<String>> rinominaCartella(@Valid @ParameterObject ModificaCartellaParams cartellaParams) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(cartelleService.rinominaCartella(cartellaParams)));
    }

    @Operation(summary = "Eliminare una cartella cartella",
            description = "Eliminare una cartella cartella e i file contenuti in essa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @DeleteMapping(value = "/elimina", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<GenericResponseDto<String>> eliminaCartella(@Valid @ParameterObject CartellaParams cartellaParams) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(cartelleService.eliminaCartella(cartellaParams)));
    }
}
