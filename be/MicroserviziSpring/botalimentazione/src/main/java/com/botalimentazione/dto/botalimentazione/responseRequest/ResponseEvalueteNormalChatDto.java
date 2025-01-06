package com.botalimentazione.dto.botalimentazione.responseRequest;

import lombok.Data;

import java.util.List;

@Data
public class ResponseEvalueteNormalChatDto {
    private String query;
    private String answer;
    private String similarity;
    private List<SourceDto> sources;
}
