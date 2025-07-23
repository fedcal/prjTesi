package com.msinfermiere.dto.infermiere.relation;

import com.msinfermiere.dto.infermiere.CartellaClinicaDto;
import com.msinfermiere.dto.infermiere.MedicinaleDto;
import lombok.Data;

@Data
public class MedicinaleCartellaDto {
    private Integer idRelazione;
    private MedicinaleDto medicinale;
    private CartellaClinicaDto cartellaClinica;
}
