package com.msinfermiere.service;

import com.msinfermiere.dto.chatbot.responseRequest.ResponseEvalueteNormalChatDto;
import com.msinfermiere.dto.chatbot.responseRequest.ResponseMessagePdfDto;
import com.msinfermiere.dto.chatbot.responseRequest.ResponseNormalMessageDto;

public interface ChatService {
    ResponseNormalMessageDto normalChat(String messagge);

    ResponseMessagePdfDto chatAddestrata(String messagge);

    ResponseEvalueteNormalChatDto evalueteNormalChat(String messagge);
}
