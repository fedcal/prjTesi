package com.msmedico.dto.medico.relation;

import com.msmedico.dto.medico.CartellaClinicaDto;
import com.msmedico.dto.medico.MedicinaleDto;
import lombok.Data;

@Data
public class MedicinaleCartellaDto {
    private Integer idRelazione;
    private MedicinaleDto medicinale;
    private CartellaClinicaDto cartellaClinica;
}
