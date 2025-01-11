package com.bff.dto.response.bot;

import lombok.Data;

@Data
public class LoadFileResponseDto {
    private String chunks;
    private String docLen;
    private String fileName;
    private String status;
}
