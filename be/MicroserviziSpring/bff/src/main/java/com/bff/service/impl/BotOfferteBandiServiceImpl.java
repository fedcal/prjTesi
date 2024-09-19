package com.bff.service.impl;

import com.bff.botoffertebandi.api.BotBandiChatControllerApi;
import com.bff.dto.botpy.responseRequest.ResponseMessagePdfDto;
import com.bff.esito.GenericResponseConverter;
import com.bff.esito.GenericResponseDto;
import com.bff.service.BotOfferteBandiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BotOfferteBandiServiceImpl implements BotOfferteBandiService {
    private final GenericResponseConverter genericResponseConverter;
    private final BotBandiChatControllerApi botBandiChatControllerApi;


    @Override
    public String normalChat(String messagge) {
        GenericResponseDto<String> normalChatResponse = genericResponseConverter.convertGenericResponse(botBandiChatControllerApi.normalChat(messagge), String.class);
        return normalChatResponse.getPayload();
    }

    @Override
    public ResponseMessagePdfDto chatAddestrata(String messagge) {
        GenericResponseDto<ResponseMessagePdfDto> chatAddestrata = genericResponseConverter.convertGenericResponse(botBandiChatControllerApi.chatAddestrata(messagge), ResponseMessagePdfDto.class);
        return chatAddestrata.getPayload();
    }
}
