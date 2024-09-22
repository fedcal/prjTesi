package com.bff.controller;

import com.bff.dto.CartelleDto;
import com.bff.dto.params.cartella.CartellaParams;
import com.bff.dto.params.cartella.ModificaCartellaParams;
import com.bff.esito.EsitoMessaggiRequestContextHolder;
import com.bff.esito.GenericResponseDto;
import com.bff.service.CartelleManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartelle-management")
@Validated
@Tag(name = "Gestione delle cartelle",
        description = "Controller dedicato alla gestione delle cartelle dei bot")
@AllArgsConstructor
public class CartelleManagementController {
    @Autowired
    private EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;
    @Autowired
    private CartelleManagementService fileManagementService;

    @Operation(summary = "Creazione della cartella",
            description = "Creazione delle cartelle di addestramento dei relativi bot")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PutMapping(value = "/cartella/crea", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<CartelleDto>> creaCartella(@Valid @RequestBody CartellaParams cartellaParams) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(fileManagementService.creaCartella(cartellaParams)));
    }

    @Operation(summary = "Aggiunta di una cartella nel db già creata",
            description = "Se la cartella esiste già, questo endpoint si occupa del salvataggio nel db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PutMapping(value = "/cartella/aggiungi", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<CartelleDto>> aggiungiCartella(@Valid @RequestBody CartellaParams cartellaParams) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(fileManagementService.aggiungiCartella(cartellaParams)));
    }

    @Operation(summary = "Rinominare una cartella cartella",
            description = "Rinominare una cartella cartella e aggiornare i path dei file contenuti in essa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/cartella/rinomina", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<CartelleDto>> rinominaCartella(@Valid @RequestBody ModificaCartellaParams cartellaParams) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(fileManagementService.rinominaCartella(cartellaParams)));
    }

    @Operation(summary = "Eliminare una cartella cartella",
            description = "Eliminare una cartella cartella e i file contenuti in essa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @DeleteMapping(value = "/cartella/elimina", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<String>> eliminaCartella(CartellaParams cartellaParams) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(fileManagementService.eliminaCartella(cartellaParams)));
    }

    @Operation(summary = "Elenco cartelle",
            description = "Elenco delle cartelle presenti nel db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/cartella/elenco", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<List<CartelleDto>>> elencoCartelle() {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(fileManagementService.elencoCartelle()));
    }

    @Operation(summary = "Ritrovamento di una cartella",
            description = "Filtrare una cartella nel db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/cartella/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<CartelleDto>> findCartella(CartellaParams cartellaParams) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(fileManagementService.findCartella(cartellaParams)));
    }
}
