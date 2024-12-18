package com.mspazienti.service;

import com.mspazienti.dto.chatbot.ResponseMessagePdfDto;

public interface ChatService {
    String normalChat(String messagge);

    ResponseMessagePdfDto chatAddestrata(String messagge);
}
