package com.msinfermiere.controller;

import com.msinfermiere.dto.chatbot.responseRequest.ResponseEvalueteNormalChatDto;
import com.msinfermiere.dto.chatbot.responseRequest.ResponseMessagePdfDto;
import com.msinfermiere.dto.chatbot.responseRequest.ResponseNormalMessageDto;
import com.msinfermiere.esito.EsitoMessaggiRequestContextHolder;
import com.msinfermiere.esito.GenericResponseDto;
import com.msinfermiere.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.msinfermiere.constants.WebConstants.MS_INFERMIERI;

@RestController
@RequestMapping(MS_INFERMIERI + "/bot-sanitario-chat")
@Validated
@Tag(name = "MsInfermiereChatBotSanitarioController",
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
