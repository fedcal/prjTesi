package com.msinfermiere.dto.infermiere;

import lombok.Data;

@Data
public class DiagnosiDto {
    private Integer idDiagnosi;
    private String tipoDiagnosi;
    private String descrizione;
    private CartellaClinicaDto cartellaClinica;
}
