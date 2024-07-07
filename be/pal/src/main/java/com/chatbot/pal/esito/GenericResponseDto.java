package com.chatbot.pal.esito;

import lombok.Data;

@Data
public class GenericResponseDto <T>{
    private Esito esito;
    private T payload;
}
