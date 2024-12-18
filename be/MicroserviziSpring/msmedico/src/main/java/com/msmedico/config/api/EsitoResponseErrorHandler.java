package com.msmedico.config.api;

import com.msmedico.esito.Esito;
import com.msmedico.esito.EsitoMessaggiRequestContextHolder;
import com.msmedico.esito.GenericResponseConverter;
import com.msmedico.esito.GenericResponseDto;
import com.msmedico.exception.EsitoRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class EsitoResponseErrorHandler extends DefaultResponseErrorHandler {
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
