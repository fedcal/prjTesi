package com.msinfermiere.dto.chatbot.responseRequest;

import lombok.Data;

import java.util.List;

@Data
public class ResponseEvalueteNormalChatDto {
    private String query;
    private String answer;
    private String similarity;
    private List<SourceDto> sources;
}
