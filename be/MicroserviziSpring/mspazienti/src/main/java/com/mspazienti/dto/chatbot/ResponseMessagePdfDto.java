package com.mspazienti.dto.chatbot;

import lombok.Data;

import java.util.List;

@Data
public class ResponseMessagePdfDto {
    private String query;
    private String answer;
    private List<SourceDto> sources;
}
