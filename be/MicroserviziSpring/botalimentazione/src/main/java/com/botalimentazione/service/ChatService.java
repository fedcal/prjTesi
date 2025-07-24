package com.botalimentazione.service;

import com.botalimentazione.dto.botalimentazione.bodyRequest.NormalChatRequestParams;
import com.botalimentazione.dto.botalimentazione.responseRequest.ResponseEvalueteNormalChatDto;
import com.botalimentazione.dto.botalimentazione.responseRequest.ResponseMessagePdfDto;
import com.botalimentazione.dto.botalimentazione.responseRequest.ResponseNormalMessageDto;

public interface ChatService {
    ResponseNormalMessageDto normalChat(NormalChatRequestParams normalChatRequestParams);

    ResponseMessagePdfDto chatAddestrata(NormalChatRequestParams normalChatRequestParams);

    ResponseEvalueteNormalChatDto evalueteNormalChat(NormalChatRequestParams normalChatRequestParams);
}
