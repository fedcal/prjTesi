package com.botalimentazione.service.impl;

import com.botalimentazione.constants.BotAlimentazionePyUrl;
import com.botalimentazione.dto.botalimentazione.bodyRequest.NormalChatRequestParams;
import com.botalimentazione.dto.botalimentazione.responseRequest.ResponseEvalueteNormalChatDto;
import com.botalimentazione.dto.botalimentazione.responseRequest.ResponseMessagePdfDto;
import com.botalimentazione.dto.botalimentazione.responseRequest.ResponseNormalMessageDto;
import com.botalimentazione.esito.EsitoMessaggiRequestContextHolder;
import com.botalimentazione.esito.Messaggio;
import com.botalimentazione.esito.constants.EsitoOperazioneEnum;
import com.botalimentazione.esito.constants.SeveritaMessaggioEnum;
import com.botalimentazione.exception.EsitoRuntimeException;
import com.botalimentazione.service.ChatService;
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
    public ResponseNormalMessageDto normalChat(String messagge) {
        esitoMessaggiRequestContextHolder.setOperationId("normalChat");
        String url = BotAlimentazionePyUrl.NORMAL_MESSAGE;

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
        return response.getBody();
    }

    @Override
    public ResponseMessagePdfDto chatAddestrata(String messagge) {
        esitoMessaggiRequestContextHolder.setOperationId("chatAddestrata");
        String url = BotAlimentazionePyUrl.MESSAGE_PDF;

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

    @Override
    public ResponseEvalueteNormalChatDto evalueteNormalChat(String messagge) {
        esitoMessaggiRequestContextHolder.setOperationId("evalueteNormalChat");
        String url = BotAlimentazionePyUrl.EVALUETE_NORMAL_MESSAGE;

        NormalChatRequestParams normalChatRequestParams = new NormalChatRequestParams();
        normalChatRequestParams.setQuery(messagge);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<NormalChatRequestParams> request = new HttpEntity<>(normalChatRequestParams, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ResponseEvalueteNormalChatDto> response = null;

        try {
            response = restTemplate.postForEntity(url, request, ResponseEvalueteNormalChatDto.class);
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
