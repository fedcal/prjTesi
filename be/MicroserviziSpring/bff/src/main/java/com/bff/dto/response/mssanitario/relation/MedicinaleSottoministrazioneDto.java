package com.bff.dto.response.mssanitario.relation;

import com.bff.dto.response.mssanitario.InfermiereDto;
import com.bff.dto.response.mssanitario.MedicinaleDto;
import com.bff.dto.response.mssanitario.CartellaClinicaDto;

import lombok.Data;

@Data
public class MedicinaleSottoministrazioneDto {
    private Integer idRelazione;
    private InfermiereDto infermiere;
    private MedicinaleDto medicinale;
    private CartellaClinicaDto cartellaClinica;
}
