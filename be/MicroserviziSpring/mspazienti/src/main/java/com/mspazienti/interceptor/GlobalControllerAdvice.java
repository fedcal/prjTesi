package com.mspazienti.interceptor;

import com.mspazienti.esito.Esito;
import com.mspazienti.esito.EsitoMessaggiRequestContextHolder;
import com.mspazienti.esito.GenericResponseDto;
import com.mspazienti.esito.Messaggio;
import com.mspazienti.esito.constants.EsitoOperazioneEnum;
import com.mspazienti.esito.constants.SeveritaMessaggioEnum;
import com.mspazienti.exception.EsitoRuntimeException;
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
