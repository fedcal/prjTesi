package com.bff.dto.response.bot;

import lombok.Data;

import java.util.List;

@Data
public class ResponseMessagePdfDto {
    private String query;
    private String answer;
    private List<SourceDto> sources;
}
