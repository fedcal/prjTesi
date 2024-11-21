package com.bff.service.impl;

import com.bff.botalimentazione.api.BotAlimentazioneChatControllerApi;
import com.bff.botoffertebandi.api.BotBandiChatControllerApi;
import com.bff.dto.botpy.responseRequest.ResponseMessagePdfDto;
import com.bff.esito.GenericResponseConverter;
import com.bff.esito.GenericResponseDto;
import com.bff.service.BotAlimentazioneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BotAlimentazioneServiceImpl implements BotAlimentazioneService {
    private final GenericResponseConverter genericResponseConverter;
    private final BotAlimentazioneChatControllerApi botAlimentazioneChatControllerApi;


    @Override
    public String normalChat(String messagge) {
        GenericResponseDto<String> normalChatResponse = genericResponseConverter.convertGenericResponse(botAlimentazioneChatControllerApi.normalChat(messagge), String.class);
        return normalChatResponse.getPayload();
    }

    @Override
    public ResponseMessagePdfDto chatAddestrata(String messagge) {
        GenericResponseDto<ResponseMessagePdfDto> chatAddestrata = genericResponseConverter.convertGenericResponse(botAlimentazioneChatControllerApi.chatAddestrata(messagge), ResponseMessagePdfDto.class);
        return chatAddestrata.getPayload();
    }

}
