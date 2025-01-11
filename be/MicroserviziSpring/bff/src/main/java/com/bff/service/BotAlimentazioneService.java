package com.bff.service;

import com.bff.dto.response.bot.ResponseEvalueteNormalChatDto;
import com.bff.dto.response.bot.ResponseMessagePdfDto;
import com.bff.dto.response.bot.ResponseNormalMessageDto;

public interface BotAlimentazioneService {
    ResponseNormalMessageDto normalChat(String messagge);

    ResponseMessagePdfDto chatAddestrata(String messagge);

    ResponseEvalueteNormalChatDto evalueteNormalChat(String messagge);
}
