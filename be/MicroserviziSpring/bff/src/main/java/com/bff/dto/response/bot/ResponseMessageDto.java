package com.bff.dto.response.bot;

import lombok.Data;

@Data
public class ResponseMessageDto {
    private String query;
    private String answer;
}
