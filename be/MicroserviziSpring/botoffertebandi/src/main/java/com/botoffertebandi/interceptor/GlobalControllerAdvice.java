package com.botoffertebandi.interceptor;

import com.botoffertebandi.esito.Esito;
import com.botoffertebandi.esito.EsitoMessaggiRequestContextHolder;
import com.botoffertebandi.esito.GenericResponseDto;
import com.botoffertebandi.esito.Messaggio;
import com.botoffertebandi.esito.constants.EsitoOperazioneEnum;
import com.botoffertebandi.esito.constants.SeveritaMessaggioEnum;
import com.botoffertebandi.exception.EsitoRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;

    @ExceptionHandler(EsitoRuntimeException.class)
    public ResponseEntity<GenericResponseDto<Void>> manageEsitoRuntimeException(EsitoRuntimeException e) {

        Esito esito = new Esito();
        esito.setCodRet(EsitoOperazioneEnum.KO);
        esito.setOperationId(esitoMessaggiRequestContextHolder.getOperationId());
        esito.setMessaggi(esitoMessaggiRequestContextHolder.getMessaggi());

        GenericResponseDto<Void> responseDto = new GenericResponseDto<>();
        responseDto.setEsito(esito);

        return new ResponseEntity<>(responseDto, e.getReturnStatus());
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<GenericResponseDto<Void>> manageResourceAccessException(ResourceAccessException e) {

        Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.ERROR).codMsg("ERR998").build();
        esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);

        Esito esito = new Esito();
        esito.setCodRet(EsitoOperazioneEnum.KO);
        esito.setOperationId("generic");
        esito.setMessaggi(esitoMessaggiRequestContextHolder.getMessaggi());

        GenericResponseDto<Void> responseDto = new GenericResponseDto<>();
        responseDto.setEsito(esito);

        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}