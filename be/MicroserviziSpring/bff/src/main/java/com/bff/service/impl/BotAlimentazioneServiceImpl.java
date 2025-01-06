package com.bff.service.impl;

import com.bff.botalimentazione.api.BotAlimentazioneChatControllerApi;
import com.bff.dto.botpy.responseRequest.ResponseEvalueteNormalChatDto;
import com.bff.dto.botpy.responseRequest.ResponseMessageDto;
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
    public ResponseMessageDto normalChat(String messagge) {
        GenericResponseDto<ResponseMessageDto> normalChatResponse = genericResponseConverter.convertGenericResponse(botAlimentazioneChatControllerApi.normalChat(messagge), ResponseMessageDto.class);
        return normalChatResponse.getPayload();
    }

    @Override
    public ResponseMessagePdfDto chatAddestrata(String messagge) {
        GenericResponseDto<ResponseMessagePdfDto> chatAddestrata = genericResponseConverter.convertGenericResponse(botAlimentazioneChatControllerApi.chatAddestrata(messagge), ResponseMessagePdfDto.class);
        return chatAddestrata.getPayload();
    }

    @Override
    public ResponseEvalueteNormalChatDto evalueteNormalChat(String messagge) {
        GenericResponseDto<ResponseEvalueteNormalChatDto> evaluationeChatAddestrata = genericResponseConverter.convertGenericResponse(botAlimentazioneChatControllerApi.chatAddestrata(messagge), ResponseEvalueteNormalChatDto.class);
        return evaluationeChatAddestrata.getPayload();
    }

}
