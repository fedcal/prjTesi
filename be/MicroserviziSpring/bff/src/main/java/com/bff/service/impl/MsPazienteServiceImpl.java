package com.bff.service.impl;

import com.bff.dto.botpy.responseRequest.ResponseEvalueteNormalChatDto;
import com.bff.dto.botpy.responseRequest.ResponseMessageDto;
import com.bff.dto.botpy.responseRequest.ResponseMessagePdfDto;
import com.bff.esito.GenericResponseConverter;
import com.bff.esito.GenericResponseDto;
import com.bff.mspaziente.api.MsPazienteChatbotControllerApi;
import com.bff.mspaziente.api.MsPazienteControllerApi;
import com.bff.service.MsPazienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MsPazienteServiceImpl implements MsPazienteService {
    private final MsPazienteChatbotControllerApi msPazienteChatbotControllerApi;
    private final MsPazienteControllerApi msPazienteControllerApi;
    private final GenericResponseConverter genericResponseConverter;

    @Override
    public ResponseMessageDto normalChat(String messagge) {
        GenericResponseDto<ResponseMessageDto> normalChatResponse = genericResponseConverter.convertGenericResponse(msPazienteChatbotControllerApi.normalChat(messagge), ResponseMessageDto.class);
        return normalChatResponse.getPayload();
    }

    @Override
    public ResponseMessagePdfDto chatAddestrata(String messagge) {
        GenericResponseDto<ResponseMessagePdfDto> chatAddestrata = genericResponseConverter.convertGenericResponse(msPazienteChatbotControllerApi.chatAddestrata(messagge), ResponseMessagePdfDto.class);
        return chatAddestrata.getPayload();
    }

    @Override
    public ResponseEvalueteNormalChatDto evalueteNormalChat(String messagge) {
        GenericResponseDto<ResponseEvalueteNormalChatDto> evaluationeChatAddestrata = genericResponseConverter.convertGenericResponse(msPazienteChatbotControllerApi.chatAddestrata(messagge), ResponseEvalueteNormalChatDto.class);
        return evaluationeChatAddestrata.getPayload();
    }
}
