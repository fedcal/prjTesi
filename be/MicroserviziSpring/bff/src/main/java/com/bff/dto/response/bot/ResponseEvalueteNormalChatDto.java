package com.bff.dto.response.bot;

import lombok.Data;

import java.util.List;

@Data
public class ResponseEvalueteNormalChatDto {
    private String query;
    private String answer;
    private String similarity;
    private List<SourceDto> sources;
}
