package com.bff.controller;

import com.bff.dto.botpy.responseRequest.ResponseMessagePdfDto;
import com.bff.esito.EsitoMessaggiRequestContextHolder;
import com.bff.esito.GenericResponseDto;
import com.bff.service.BotAiService;
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
@RequestMapping("/bot-ai-chat")
@Validated
@Tag(name = "Bot Ai Chat Controller",
        description = "Gestione della chat relativa al bot sull'argomento ai")
@AllArgsConstructor
public class BotAiController {
    @Autowired
    private EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;
    @Autowired
    private BotAiService botAiService;

    @Operation(summary = "Chat normale",
            description = "Invio di un messaggio al bot AI sfruttando l'LLM non addestrato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/normal-chat", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<String>> normalChat(@RequestParam String messagge) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(botAiService.normalChat(messagge)));
    }

    @Operation(summary = "Chat addestrata",
            description = "Invio di un messaggio al bot AI sfruttando l'LLM addestrato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/chat-addestrata", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<ResponseMessagePdfDto>> chatAddestrata(@RequestParam String messagge) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(botAiService.chatAddestrata(messagge)));
    }
}
