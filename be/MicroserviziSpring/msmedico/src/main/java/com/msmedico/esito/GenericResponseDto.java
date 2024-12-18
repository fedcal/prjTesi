package com.msmedico.esito;

import lombok.Data;

@Data
public class GenericResponseDto <T>{
    private Esito esito;
    private T payload;
}
