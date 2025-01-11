package com.bff.dto.request.msinfermiere;

import lombok.Data;

@Data
public class FilterPazienteDto {
    private Integer idPaziente;
    private String nomePaziente;
    private String cognomePaziente;
    private Integer idReparto;
    private String dataNascita;
    private String luogoNascita;
    private String provinciaNascita;
    private Integer contattoRiferimento;
}
