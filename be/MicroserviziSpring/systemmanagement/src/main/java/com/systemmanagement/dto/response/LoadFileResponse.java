package com.systemmanagement.dto.response;

import lombok.Data;

@Data
public class LoadFileResponse {
    private String chunks;
    private String docLen;
    private String fileName;
    private String status;
}
