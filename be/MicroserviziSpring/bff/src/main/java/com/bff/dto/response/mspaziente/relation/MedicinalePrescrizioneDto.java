package com.bff.dto.response.mspaziente.relation;

import com.bff.dto.response.mspaziente.CartellaClinicaDto;
import com.bff.dto.response.mspaziente.MedicinaleDto;
import com.bff.dto.response.mspaziente.MedicoDto;
import lombok.Data;

@Data
public class MedicinalePrescrizioneDto {
    private Integer idRelazione;
    private MedicoDto medico;
    private MedicinaleDto medicinale;
    private CartellaClinicaDto cartellaClinica;
}
