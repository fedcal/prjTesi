package com.botalimentazione.controller;

import com.botalimentazione.dto.botalimentazione.responseRequest.ResponseEvalueteNormalChatDto;
import com.botalimentazione.dto.botalimentazione.responseRequest.ResponseMessagePdfDto;
import com.botalimentazione.dto.botalimentazione.responseRequest.ResponseNormalMessageDto;
import com.botalimentazione.esito.EsitoMessaggiRequestContextHolder;
import com.botalimentazione.esito.GenericResponseDto;
import com.botalimentazione.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bot-alimentazione-chat")
@Validated
@Tag(name = "Bot Alimentazione Chat Controller",
        description = "Gestione della chat")
@CrossOrigin
@AllArgsConstructor
public class BotAlimentazioneController {
    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;
    private final ChatService chatService;

    @Operation(summary = "Chat normale",
            description = "Invio di un messaggio al bot Alimentazione sfruttando l'LLM non addestrato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/normal-chat", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<ResponseNormalMessageDto>> normalChat(@RequestParam String messagge) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(chatService.normalChat(messagge)));
    }

    @Operation(summary = "Chat addestrata",
            description = "Invio di un messaggio al bot Alimentazione sfruttando l'LLM addestrato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/chat-addestrata", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<ResponseMessagePdfDto>> chatAddestrata(@RequestParam String messagge) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(chatService.chatAddestrata(messagge)));
    }

    @Operation(summary = "Chat Valutazione pdf",
            description = "Chat Valutazione pdf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/evaluete-normal-chat", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<ResponseEvalueteNormalChatDto>> evalueteNormalChat(@RequestParam String messagge) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(chatService.evalueteNormalChat(messagge)));
    }
}
