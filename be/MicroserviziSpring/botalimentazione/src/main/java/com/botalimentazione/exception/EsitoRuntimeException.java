package com.botalimentazione.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class EsitoRuntimeException extends RuntimeException{
    private HttpStatus returnStatus;
}
