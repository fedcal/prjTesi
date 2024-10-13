package com.systemmanagement.controller;


import com.systemmanagement.dto.RagBotPdfDto;
import com.systemmanagement.dto.params.ragbotpdf.ModificaBotParams;
import com.systemmanagement.dto.params.ragbotpdf.RegistraBotParams;
import com.systemmanagement.dto.params.ragbotpdf.TrovaBotParams;
import com.systemmanagement.esito.EsitoMessaggiRequestContextHolder;
import com.systemmanagement.esito.GenericResponseDto;
import com.systemmanagement.service.RagBotPdfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/ragbot")
@Tag(name = "Rag bot Controller",
        description = "Gestione dei bot rag")
@AllArgsConstructor
public class RagBotController {

    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;

    private final RagBotPdfService ragBotPdfService;

    @Operation(summary = "Elenco bot",
            description = "Elenco dei bot registrati nel database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/visualizza", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<List<RagBotPdfDto>>> elencoBot() {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(ragBotPdfService.elencoBot()));
    }

    @Operation(summary = "Trova bot",
            description = "Trova un bot nel database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/cerca", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<RagBotPdfDto>> trovaBot(TrovaBotParams trovaBotParam) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(ragBotPdfService.trovaBot(trovaBotParam)));
    }

    @Operation(summary = "Registra bot",
            description = "Registrazione di un bot all'interno del database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/registra", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<RagBotPdfDto>> registraBot(@RequestBody RegistraBotParams registraBotParams) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(ragBotPdfService.registraBot(registraBotParams)));
    }

    @Operation(summary = "Modifica bot",
            description = "Modifica le informazioni del bot registrate nel database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/modifica", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<RagBotPdfDto>> modificaBot(@RequestBody ModificaBotParams modificaBotParams) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(ragBotPdfService.modificaBot(modificaBotParams)));
    }

    @Operation(summary = "Elemina bot",
            description = "Elimina un bot nel database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @DeleteMapping(value = "/elimina", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<String>> eliminaBot(@RequestParam String nomeBot) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(ragBotPdfService.eliminaBot(nomeBot)));
    }

}
