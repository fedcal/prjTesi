package com.bff.service.impl;

import com.bff.dto.response.bot.ResponseEvalueteNormalChatDto;
import com.bff.dto.response.bot.ResponseMessagePdfDto;
import com.bff.dto.response.bot.ResponseNormalMessageDto;
import com.bff.esito.GenericResponseConverter;
import com.bff.esito.GenericResponseDto;
import com.bff.msinfermiere.api.MsInfermiereChatBotSanitarioControllerApi;
import com.bff.service.MsInfermiereService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MsInfermiereServiceImpl implements MsInfermiereService {
    private final GenericResponseConverter genericResponseConverter;
    private final MsInfermiereChatBotSanitarioControllerApi msInfermiereChatBotSanitarioControllerApi;

    @Override
    public ResponseNormalMessageDto normalChat(String messagge) {
        GenericResponseDto<ResponseNormalMessageDto> normalChatResponse = genericResponseConverter.convertGenericResponse(msInfermiereChatBotSanitarioControllerApi.normalChat(messagge), ResponseNormalMessageDto.class);
        return normalChatResponse.getPayload();
    }

    @Override
    public ResponseMessagePdfDto chatAddestrata(String messagge) {
        GenericResponseDto<ResponseMessagePdfDto> chatAddestrata = genericResponseConverter.convertGenericResponse(msInfermiereChatBotSanitarioControllerApi.chatAddestrata(messagge), ResponseMessagePdfDto.class);
        return chatAddestrata.getPayload();
    }

    //TODO CAMBIARE METODO IN CONVERTGENERIC RESPONSE
    @Override
    public ResponseEvalueteNormalChatDto evalueteNormalChat(String messagge) {
        GenericResponseDto<ResponseEvalueteNormalChatDto> evaluationeChatAddestrata = genericResponseConverter.convertGenericResponse(msInfermiereChatBotSanitarioControllerApi.evalueteNormalChat(messagge), ResponseEvalueteNormalChatDto.class);
        return evaluationeChatAddestrata.getPayload();
    }
}
