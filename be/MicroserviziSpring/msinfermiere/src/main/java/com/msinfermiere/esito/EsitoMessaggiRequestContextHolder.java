package com.msinfermiere.esito;

import com.msinfermiere.esito.constants.EsitoOperazioneEnum;
import com.msinfermiere.esito.constants.SeveritaMessaggioEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EsitoMessaggiRequestContextHolder {
    private final List<Messaggio> messaggi = new ArrayList<>();

    private EsitoOperazioneEnum codRet;
    private  String operationId;

    public <T> GenericResponseDto<T> buildGenericResponse(T payload) {

        Esito esito = new Esito();
        esito.setCodRet(messaggi.stream().anyMatch(m -> SeveritaMessaggioEnum.WARNING.equals(m.getSeverita())) ? EsitoOperazioneEnum.WARNING : EsitoOperazioneEnum.OK);
        esito.setMessaggi(messaggi);
        esito.setCodRet(codRet);
        esito.setOperationId(operationId);

        GenericResponseDto<T> responseDto = new GenericResponseDto<>();
        responseDto.setEsito(esito);
        responseDto.setPayload(payload);

        return responseDto;
    }
}
