package com.systemmanagement.dto;


import lombok.Data;

@Data
public class CartelleDto {
    private Long idCartella;
    private String nomeCartella;
    private String path;
    private Boolean isCartellaAddestramento;
}
