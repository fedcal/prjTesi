package com.bff.dto.response.mspaziente.relation;

import com.bff.dto.response.mspaziente.MedicinaleDto;
import com.bff.dto.response.mspaziente.CartellaClinicaDto;
import lombok.Data;

@Data
public class MedicinaleCartellaDto {
    private Integer idRelazione;
    private MedicinaleDto medicinale;
    private CartellaClinicaDto cartellaClinica;
}
