package com.botbandi.service;

import com.botbandi.dto.botpy.responseRequest.ResponseMessagePdfDto;

public interface ChatService {
    String normalChat(String messagge);

    ResponseMessagePdfDto chatAddestrata(String messagge);
}
