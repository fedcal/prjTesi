package com.bff.controller;

import com.bff.dto.RagBotPdfDto;
import com.bff.esito.EsitoMessaggiRequestContextHolder;
import com.bff.esito.GenericResponseDto;
import com.bff.service.RagBotManagementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.bff.dto.params.ragbotpdf.RegistraBotParams;
import com.bff.dto.params.ragbotpdf.ModificaBotParams;

import java.util.List;

@RestController
@RequestMapping("/reag-bot-management")
@Validated
@Tag(name = "Gestione file Controller",
        description = "Gestione dei file")
@CrossOrigin
@AllArgsConstructor
public class RagBotManagementController {
    @Autowired
    private EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;

    @Autowired
    private RagBotManagementService ragBotManagementService;

    @GetMapping(value = "/visualizza", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<List<RagBotPdfDto>>> elencoBot() {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(ragBotManagementService.elencoBot()));
    }

    @PostMapping(value = "/registra", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<RagBotPdfDto>> registraBot(@RequestBody RegistraBotParams registraBotParams) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(ragBotManagementService.registraBot(registraBotParams)));
    }

    @PostMapping(value = "/modifica", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<RagBotPdfDto>> modificaBot(@RequestBody ModificaBotParams modificaBotParams) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(ragBotManagementService.modificaBot(modificaBotParams)));
    }

    @DeleteMapping(value = "/elimina", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<String>> eliminaBot(@RequestParam String nomeBot) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(ragBotManagementService.eliminaBot(nomeBot)));
    }
}
