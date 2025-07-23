package com.bff.dto.response.mssanitario.operazione;

import lombok.Data;

@Data
public class RefertoOperazioneDto {
    private Integer idReferto;
    private String tipologia;
    private String descrizione;
    private String dataReferto;
}
