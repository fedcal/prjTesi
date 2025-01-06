package com.bff.dto.response.mspaziente;

import lombok.Data;

@Data
public class MedicinaleSottoministrazioneDto {
    private Integer idRelazione;
    private InfermiereDto infermiere;
    private MedicinaleDto medicinale;
    private CartellaClinicaDto cartellaClinica;
}
