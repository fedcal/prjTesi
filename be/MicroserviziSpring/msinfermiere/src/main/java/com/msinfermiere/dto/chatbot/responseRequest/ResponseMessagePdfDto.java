package com.msinfermiere.dto.chatbot.responseRequest;

import lombok.Data;

import java.util.List;

@Data
public class ResponseMessagePdfDto {
    private String answer;
    private List<SourceDto> sources;
}
