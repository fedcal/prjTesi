package com.systemmanagement.controller;


import com.systemmanagement.dto.RagBotPdfDto;
import com.systemmanagement.dto.params.ragbotpdf.ModificaBotParams;
import com.systemmanagement.dto.params.ragbotpdf.RegistraBotParams;
import com.systemmanagement.esito.EsitoMessaggiRequestContextHolder;
import com.systemmanagement.esito.GenericResponseDto;
import com.systemmanagement.service.RagBotPdfService;
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
@CrossOrigin
@AllArgsConstructor
public class RagBotController {

    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;

    private final RagBotPdfService ragBotPdfService;

    @GetMapping(value = "/visualizza", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<List<RagBotPdfDto>>> elencoBot() {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(ragBotPdfService.elencoBot()));
    }

    @PostMapping(value = "/registra", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<RagBotPdfDto>> registraBot(@RequestBody RegistraBotParams registraBotParams) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(ragBotPdfService.registraBot(registraBotParams)));
    }

    @PostMapping(value = "/modifica", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<RagBotPdfDto>> modificaBot(@RequestBody ModificaBotParams modificaBotParams) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(ragBotPdfService.modificaBot(modificaBotParams)));
    }

    @DeleteMapping(value = "/elimina", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<String>> eliminaBot(@RequestParam String nomeBot) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(ragBotPdfService.eliminaBot(nomeBot)));
    }

}
