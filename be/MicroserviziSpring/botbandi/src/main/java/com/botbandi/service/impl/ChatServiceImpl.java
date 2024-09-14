package com.botbandi.service.impl;

import com.botbandi.constants.BotAiPyUrl;
import com.botbandi.dto.botpy.bodyRequest.NormalChatRequestParams;
import com.botbandi.dto.botpy.responseRequest.ResponseMessagePdfDto;
import com.botbandi.dto.botpy.responseRequest.ResponseNormalMessageDto;
import com.botbandi.esito.EsitoMessaggiRequestContextHolder;
import com.botbandi.esito.Messaggio;
import com.botbandi.esito.constants.EsitoOperazioneEnum;
import com.botbandi.esito.constants.SeveritaMessaggioEnum;
import com.botbandi.exception.EsitoRuntimeException;
import com.botbandi.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;


    @Override
    public String normalChat(String messagge) {
        esitoMessaggiRequestContextHolder.setOperationId("normalChat");
        String url = BotAiPyUrl.NORMAL_MESSAGE;

        NormalChatRequestParams normalChatRequestParams = new NormalChatRequestParams();
        normalChatRequestParams.setQuery(messagge);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<NormalChatRequestParams> request = new HttpEntity<>(normalChatRequestParams, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ResponseNormalMessageDto> response = null;

        try {
            response = restTemplate.postForEntity(url, request, ResponseNormalMessageDto.class);
        } catch (RestClientException e) {
            if(e.getMessage().contains("port=11434")){
                Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Il modello non risponde.").build();
                esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
                esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
                throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
            }else{
                Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Il relativo servizio in python non è attivo.").build();
                esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
                esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
                throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
            }
        }

        esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
        return response.getBody().getMessage();
    }

    @Override
    public ResponseMessagePdfDto chatAddestrata(String messagge) {
        esitoMessaggiRequestContextHolder.setOperationId("chatAddestrata");
        String url = BotAiPyUrl.MESSAGE_PDF;

        NormalChatRequestParams normalChatRequestParams = new NormalChatRequestParams();
        normalChatRequestParams.setQuery(messagge);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<NormalChatRequestParams> request = new HttpEntity<>(normalChatRequestParams, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ResponseMessagePdfDto> response = null;

        try {
            response = restTemplate.postForEntity(url, request, ResponseMessagePdfDto.class);
        } catch (RestClientException e) {
            if(e.getMessage().contains("port=11434")){
                Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Il modello non risponde.").build();
                esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
                esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
                throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
            }else{
                Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Il relativo servizio in python non è attivo.").build();
                esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
                esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
                throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
            }
        }

        esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
        return response.getBody();
    }
}
