package com.bff.service;

import com.bff.dto.botpy.responseRequest.ResponseMessagePdfDto;

public interface BotOfferteBandiService {
    String normalChat(String messagge);

    ResponseMessagePdfDto chatAddestrata(String messagge);
}
