package com.msmedico.service;

import com.msmedico.dto.chatbot.ResponseMessagePdfDto;

public interface ChatService {
    String normalChat(String messagge);

    ResponseMessagePdfDto chatAddestrata(String messagge);
}
