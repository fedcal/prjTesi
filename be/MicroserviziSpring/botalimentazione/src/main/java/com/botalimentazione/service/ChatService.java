package com.botalimentazione.service;

import com.botalimentazione.dto.botalimentazione.responseRequest.ResponseMessagePdfDto;

public interface ChatService {
    String normalChat(String messagge);

    ResponseMessagePdfDto chatAddestrata(String messagge);
}
