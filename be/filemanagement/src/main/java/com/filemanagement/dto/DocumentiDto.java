package com.filemanagement.dto;

import lombok.Data;

@Data
public class DocumentiDto {
    private Long idDocumento;
    private String nomeDocumento;
    private String estensioneDocumento;
    private String path;
}
