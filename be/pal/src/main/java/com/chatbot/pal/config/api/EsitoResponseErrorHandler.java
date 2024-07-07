package com.chatbot.pal.config.api;

import com.chatbot.pal.esito.Esito;
import com.chatbot.pal.esito.EsitoMessaggiRequestContextHolder;
import com.chatbot.pal.esito.GenericResponseConverter;
import com.chatbot.pal.esito.GenericResponseDto;
import com.chatbot.pal.exception.EsitoRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class EsitoResponseErrorHandler extends DefaultResponseErrorHandler{
    private final GenericResponseConverter genericResponseConverter;

    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        GenericResponseDto<Void> responseDto = genericResponseConverter.convertGenericResponse(response.getBody());
        Esito esito = responseDto.getEsito();
        esitoMessaggiRequestContextHolder.getMessaggi().addAll(esito.getMessaggi());
        throw new EsitoRuntimeException((HttpStatus) response.getStatusCode());
    }
}
