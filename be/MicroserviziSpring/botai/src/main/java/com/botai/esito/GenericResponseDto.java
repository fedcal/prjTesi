package com.botai.esito;

import lombok.Data;

@Data
public class GenericResponseDto <T>{
    private Esito esito;
    private T payload;
}
