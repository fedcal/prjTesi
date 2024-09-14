package com.bff.esito;

import com.bff.dto.CustomPageImpl;
import com.bff.esito.constants.SeveritaMessaggioEnum;
import com.bff.exception.EsitoRuntimeException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenericResponseConverter {
    private final ObjectMapper objectMapper;

    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;

    public <T> GenericResponseDto<T> convertGenericResponse(Object response, Class<T> payloadClass) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(GenericResponseDto.class, payloadClass);
            GenericResponseDto<T> genericResponseDto = objectMapper.readValue(objectMapper.writeValueAsBytes(response), javaType);
            esitoMessaggiRequestContextHolder.getMessaggi().addAll(genericResponseDto.getEsito().getMessaggi());
            return genericResponseDto;
        } catch (IOException e) {
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.ERROR).codMsg("ERR999").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            throw new EsitoRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public <T> GenericResponseDto<List<T>> convertGenericResponseList(Object response, Class<T> payloadClass) {
        try {
            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, payloadClass);
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(GenericResponseDto.class, collectionType);
            GenericResponseDto<List<T>> listGenericResponseDto = objectMapper.readValue(objectMapper.writeValueAsBytes(response), javaType);
            esitoMessaggiRequestContextHolder.getMessaggi().addAll(listGenericResponseDto.getEsito().getMessaggi());
            return listGenericResponseDto;
        } catch (IOException e) {
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.ERROR).codMsg("ERR999").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            throw new EsitoRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public <T> GenericResponseDto<Page<T>> convertGenericResponsePage(Object response, Class<T> payloadClass) {
        try {
            JavaType innerType = objectMapper.getTypeFactory().constructParametricType(CustomPageImpl.class, payloadClass);
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(GenericResponseDto.class, innerType);
            GenericResponseDto<Page<T>> pageGenericResponseDto = objectMapper.readValue(objectMapper.writeValueAsBytes(response), javaType);
            esitoMessaggiRequestContextHolder.getMessaggi().addAll(pageGenericResponseDto.getEsito().getMessaggi());
            return pageGenericResponseDto;
        } catch (IOException e) {
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.ERROR).codMsg("ERR999").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            throw new EsitoRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public GenericResponseDto<Void> convertGenericResponse(InputStream inputStream) {
        try {
            objectMapper.getFactory().disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
            TypeReference<GenericResponseDto<Void>> typeReference = new TypeReference<>() {
            };
            return objectMapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.ERROR).codMsg("ERR999").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            throw new EsitoRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
