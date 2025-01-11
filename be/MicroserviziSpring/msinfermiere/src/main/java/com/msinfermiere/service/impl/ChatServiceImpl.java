package com.msinfermiere.service.impl;

import com.msinfermiere.constants.BotSanitarioPyUrl;
import com.msinfermiere.dto.chatbot.bodyRequest.NormalChatRequestParams;
import com.msinfermiere.dto.chatbot.responseRequest.ResponseEvalueteNormalChatDto;
import com.msinfermiere.dto.chatbot.responseRequest.ResponseMessagePdfDto;
import com.msinfermiere.dto.chatbot.responseRequest.ResponseNormalMessageDto;
import com.msinfermiere.esito.EsitoMessaggiRequestContextHolder;
import com.msinfermiere.esito.Messaggio;
import com.msinfermiere.esito.constants.EsitoOperazioneEnum;
import com.msinfermiere.esito.constants.SeveritaMessaggioEnum;
import com.msinfermiere.exception.EsitoRuntimeException;
import com.msinfermiere.service.ChatService;
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
        String url = BotSanitarioPyUrl.NORMAL_MESSAGE;

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
        String url = BotSanitarioPyUrl.MESSAGE_PDF;

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
        String url = BotSanitarioPyUrl.EVALUETE_NORMAL_MESSAGE;

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
