package com.mspazienti.dto.paziente.relation;

import com.mspazienti.dto.paziente.CartellaClinicaDto;
import com.mspazienti.dto.paziente.MedicinaleDto;
import lombok.Data;

@Data
public class MedicinaleCartellaDto {
    private Integer idRelazione;
    private MedicinaleDto medicinale;
    private CartellaClinicaDto cartellaClinica;
}
