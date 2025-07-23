package com.msmedico.dto.medico.relation;

import com.msmedico.dto.medico.CartellaClinicaDto;
import com.msmedico.dto.medico.MedicinaleDto;
import com.msmedico.dto.medico.MedicoDto;
import lombok.Data;

@Data
public class MedicinalePrescrizioneDto {
    private Integer idRelazione;
    private MedicoDto medico;
    private MedicinaleDto medicinale;
    private CartellaClinicaDto cartellaClinica;
}
