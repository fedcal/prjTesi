package com.msinfermiere.service;

import com.msinfermiere.dto.chatbot.responseRequest.ResponseMessagePdfDto;

public interface ChatService {
    String normalChat(String messagge);

    ResponseMessagePdfDto chatAddestrata(String messagge);
}
