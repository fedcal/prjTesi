package com.bff.controller;

import com.bff.dto.botpy.responseRequest.ResponseMessagePdfDto;
import com.bff.esito.EsitoMessaggiRequestContextHolder;
import com.bff.esito.GenericResponseDto;
import com.bff.service.BotOfferteBandiService;
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

@RestController
@RequestMapping("/bot-offerte-bandi-chat")
@Validated
@Tag(name = "Bot Offerte Bandi Chat Controller",
        description = "Gestione della chat offerte bandi")
@CrossOrigin
@AllArgsConstructor
public class BotOfferteBandiController {
    @Autowired
    private EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;
    @Autowired
    private BotOfferteBandiService botOfferteBandiService;

    @Operation(summary = "Invio messaggio chat normale",
            description = "Invio di un messaggio al bot ai")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/normal-chat", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<String>> normalChat(@RequestParam String messagge) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(botOfferteBandiService.normalChat(messagge)));
    }

    @Operation(summary = "Invio messaggio chat addestrata",
            description = "Messaggio e risposta alla chat addestrata per chiedere informazioni relative ai file su cui è addestrato il bot")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/chat-addestrata", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<ResponseMessagePdfDto>> chatAddestrata(@RequestParam String messagge) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(botOfferteBandiService.chatAddestrata(messagge)));
    }
}
