package com.botoffertebandi.service;

import com.botoffertebandi.dto.botpy.responseRequest.ResponseMessagePdfDto;

public interface ChatService {
    String normalChat(String messagge);

    ResponseMessagePdfDto chatAddestrata(String messagge);
}
