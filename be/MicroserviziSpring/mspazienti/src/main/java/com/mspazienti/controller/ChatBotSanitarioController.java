package com.mspazienti.controller;

import com.mspazienti.dto.chatbot.ResponseEvalueteNormalChatDto;
import com.mspazienti.dto.chatbot.ResponseMessagePdfDto;
import com.mspazienti.dto.chatbot.ResponseNormalMessageDto;
import com.mspazienti.esito.EsitoMessaggiRequestContextHolder;
import com.mspazienti.esito.GenericResponseDto;
import com.mspazienti.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.mspazienti.constants.WebConstants.REST_CONTEX_STRING;

@RestController
@RequestMapping(REST_CONTEX_STRING + "/bot-sanitario-chat")
@Validated
@Tag(name = "MsPazienteChatbotController",
        description = "Gestione della chat")
@CrossOrigin
@AllArgsConstructor
public class ChatBotSanitarioController {
    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;
    private final ChatService chatService;

    @Operation(summary = "Chat normale",
            description = "Invio di un messaggio al bot Sanitario sfruttando l'LLM non addestrato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/normal-chat", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<ResponseNormalMessageDto>> normalChat(@RequestParam String messagge) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(chatService.normalChat(messagge)));
    }

    @Operation(summary = "Chat addestrata",
            description = "Invio di un messaggio al bot Sanitario sfruttando l'LLM addestrato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/chat-addestrata", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<ResponseMessagePdfDto>> chatAddestrata(@RequestParam String messagge) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(chatService.chatAddestrata(messagge)));
    }

    @Operation(summary = "Chat normale",
            description = "Invio di un messaggio al bot Alimentazione sfruttando l'LLM non addestrato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/evaluete-normal-chat", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<ResponseEvalueteNormalChatDto>> evalueteNormalChat(@RequestParam String messagge) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(chatService.evalueteNormalChat(messagge)));
    }
}
