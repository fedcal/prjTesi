package com.bff.service;

import com.bff.dto.botpy.responseRequest.ResponseEvalueteNormalChatDto;
import com.bff.dto.botpy.responseRequest.ResponseMessagePdfDto;
import com.bff.dto.botpy.responseRequest.ResponseNormalMessageDto;

public interface MsPazienteService {
    ResponseNormalMessageDto normalChat(String messagge);

    ResponseMessagePdfDto chatAddestrata(String messagge);

    ResponseEvalueteNormalChatDto evalueteNormalChat(String messagge);
}
