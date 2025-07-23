package com.bff.dto.response.mssanitario.relation;

import com.bff.dto.response.mssanitario.MedicinaleDto;
import com.bff.dto.response.mssanitario.CartellaClinicaDto;
import lombok.Data;

@Data
public class MedicinaleCartellaDto {
    private Integer idRelazione;
    private MedicinaleDto medicinale;
    private CartellaClinicaDto cartellaClinica;
}
