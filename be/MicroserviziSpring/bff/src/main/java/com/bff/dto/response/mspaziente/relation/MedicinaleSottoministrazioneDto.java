package com.bff.dto.response.mspaziente.relation;

import com.bff.dto.response.mspaziente.InfermiereDto;
import com.bff.dto.response.mspaziente.MedicinaleDto;
import com.bff.dto.response.mspaziente.CartellaClinicaDto;

import lombok.Data;

@Data
public class MedicinaleSottoministrazioneDto {
    private Integer idRelazione;
    private InfermiereDto infermiere;
    private MedicinaleDto medicinale;
    private CartellaClinicaDto cartellaClinica;
}
