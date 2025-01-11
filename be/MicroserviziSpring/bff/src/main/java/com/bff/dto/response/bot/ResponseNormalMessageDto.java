package com.bff.dto.response.bot;

import lombok.Data;

@Data
public class ResponseNormalMessageDto {
    private String query;
    private String message;
}
