package com.bff.dto.response.mspaziente;

import lombok.Data;

@Data
public class MedicinaleDto {
    private Integer idMedicinale;
    private String nomeMedicinale;
    private String descrizioneMedicinale;
}
