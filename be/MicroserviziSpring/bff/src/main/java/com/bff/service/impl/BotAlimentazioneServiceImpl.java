package com.bff.service.impl;

import com.bff.botalimentazione.api.BotAlimentazioneChatControllerApi;
import com.bff.dto.response.bot.ResponseEvalueteNormalChatDto;
import com.bff.dto.response.bot.ResponseMessagePdfDto;
import com.bff.dto.response.bot.ResponseNormalMessageDto;
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
    public ResponseNormalMessageDto normalChat(String messagge) {
        GenericResponseDto<ResponseNormalMessageDto> normalChatResponse = genericResponseConverter.convertGenericResponse(botAlimentazioneChatControllerApi.normalChat(messagge), ResponseNormalMessageDto.class);
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
