package com.botalimentazione.service;

import com.botalimentazione.dto.botalimentazione.responseRequest.ResponseEvalueteNormalChatDto;
import com.botalimentazione.dto.botalimentazione.responseRequest.ResponseMessagePdfDto;
import com.botalimentazione.dto.botalimentazione.responseRequest.ResponseNormalMessageDto;

public interface ChatService {
    ResponseNormalMessageDto normalChat(String messagge);

    ResponseMessagePdfDto chatAddestrata(String messagge);

    ResponseEvalueteNormalChatDto evalueteNormalChat(String messagge);
}
