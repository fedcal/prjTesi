package com.bff.controller;

import com.bff.dto.RagBotPdfDto;
import com.bff.dto.request.ragbotpdf.TrovaBotParams;
import com.bff.esito.EsitoMessaggiRequestContextHolder;
import com.bff.esito.GenericResponseDto;
import com.bff.service.RagBotManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.bff.dto.request.ragbotpdf.RegistraBotParams;
import com.bff.dto.request.ragbotpdf.ModificaBotParams;

import java.util.List;

@RestController
@RequestMapping("/reag-bot-management")
@Validated
@Tag(name = "Gestione bot rag",
        description = "Operazioni per registrare i bot nel db e gestire le cartelle di addestamento")
@AllArgsConstructor
public class RagBotManagementController {
    @Autowired
    private EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;

    @Autowired
    private RagBotManagementService ragBotManagementService;

    @Operation(summary = "Elenco bot",
            description = "Elenco dei bot registrati nel database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/visualizza", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<List<RagBotPdfDto>>> elencoBot() {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(ragBotManagementService.elencoBot()));
    }

    @Operation(summary = "Trova bot",
            description = "Trova un bot nel database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/cerca", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<RagBotPdfDto>> trovaBot(TrovaBotParams trovaBotParam) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(ragBotManagementService.trovaBot(trovaBotParam)));
    }

    @Operation(summary = "Registra bot",
            description = "Registrazione di un bot all'interno del database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/registra", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<RagBotPdfDto>> registraBot(@RequestBody RegistraBotParams registraBotParams) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(ragBotManagementService.registraBot(registraBotParams)));
    }

    @Operation(summary = "Modifica bot",
            description = "Modifica le informazioni del bot registrate nel database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/modifica", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<RagBotPdfDto>> modificaBot(@RequestBody ModificaBotParams modificaBotParams) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(ragBotManagementService.modificaBot(modificaBotParams)));
    }

    @Operation(summary = "Elemina bot",
            description = "Elimina un bot nel database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @DeleteMapping(value = "/elimina", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<String>> eliminaBot(@RequestParam String nomeBot) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(ragBotManagementService.eliminaBot(nomeBot)));
    }
}
