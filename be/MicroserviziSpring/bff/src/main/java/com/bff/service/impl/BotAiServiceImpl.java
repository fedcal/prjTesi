package com.bff.service.impl;

import com.bff.botai.api.BotAiChatControllerApi;
import com.bff.dto.botpy.responseRequest.ResponseMessagePdfDto;
import com.bff.esito.*;
import com.bff.service.BotAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BotAiServiceImpl implements BotAiService {
    private final BotAiChatControllerApi botAiChatControllerApi;
    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;
    private final GenericResponseConverter genericResponseConverter;

    @Override
    public String normalChat(String messagge) {
        //GenericResponseDtoString normalChatResponse = botAiChatControllerApi.normalChat(messagge);
        GenericResponseDto<String> normalChatResponse = genericResponseConverter.convertGenericResponse(botAiChatControllerApi.normalChat(messagge), String.class);
        return normalChatResponse.getPayload();
    }

    @Override
    public ResponseMessagePdfDto chatAddestrata(String messagge) {
        GenericResponseDto<ResponseMessagePdfDto> chatAddestrata = genericResponseConverter.convertGenericResponse(botAiChatControllerApi.chatAddestrata(messagge), ResponseMessagePdfDto.class);
        return chatAddestrata.getPayload();
    }
}
