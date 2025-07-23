package com.bff.service;

import com.bff.dto.response.bot.ResponseEvalueteNormalChatDto;
import com.bff.dto.response.bot.ResponseMessagePdfDto;
import com.bff.dto.response.bot.ResponseNormalMessageDto;
import com.bff.dto.response.mssanitario.PazienteDto;

import java.util.List;

public interface MsPazienteService {
    ResponseNormalMessageDto normalChat(String messagge);

    ResponseMessagePdfDto chatAddestrata(String messagge);

    ResponseEvalueteNormalChatDto evalueteNormalChat(String messagge);

    List<PazienteDto> getListPazienti();

    PazienteDto getInfoPaziente(Integer idPaziente);
}
