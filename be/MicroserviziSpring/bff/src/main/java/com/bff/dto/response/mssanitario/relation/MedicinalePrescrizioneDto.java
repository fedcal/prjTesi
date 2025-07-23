package com.bff.dto.response.mssanitario.relation;

import com.bff.dto.response.mssanitario.CartellaClinicaDto;
import com.bff.dto.response.mssanitario.MedicinaleDto;
import com.bff.dto.response.mssanitario.MedicoDto;
import lombok.Data;

@Data
public class MedicinalePrescrizioneDto {
    private Integer idRelazione;
    private MedicoDto medico;
    private MedicinaleDto medicinale;
    private CartellaClinicaDto cartellaClinica;
}
