package com.mspazienti.service;

import com.mspazienti.dto.chatbot.ResponseEvalueteNormalChatDto;
import com.mspazienti.dto.chatbot.ResponseMessagePdfDto;
import com.mspazienti.dto.chatbot.ResponseNormalMessageDto;

public interface ChatService {
    ResponseNormalMessageDto normalChat(String messagge);

    ResponseMessagePdfDto chatAddestrata(String messagge);

    ResponseEvalueteNormalChatDto evalueteNormalChat(String messagge);
}
