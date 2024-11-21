package com.bff.service;

import com.bff.dto.botpy.responseRequest.ResponseMessagePdfDto;

public interface BotAlimentazioneService {
    String normalChat(String messagge);

    ResponseMessagePdfDto chatAddestrata(String messagge);
}
