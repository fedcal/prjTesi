package com.bff.service;

import com.bff.dto.botpy.responseRequest.ResponseEvalueteNormalChatDto;
import com.bff.dto.botpy.responseRequest.ResponseMessageDto;
import com.bff.dto.botpy.responseRequest.ResponseMessagePdfDto;

public interface MsPazienteService {
    ResponseMessageDto normalChat(String messagge);

    ResponseMessagePdfDto chatAddestrata(String messagge);

    ResponseEvalueteNormalChatDto evalueteNormalChat(String messagge);
}
