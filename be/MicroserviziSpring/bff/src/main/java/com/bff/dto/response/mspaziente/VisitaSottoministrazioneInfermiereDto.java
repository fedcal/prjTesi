package com.bff.dto.response.mspaziente;

import lombok.Data;

@Data
public class VisitaSottoministrazioneInfermiereDto {
    private Integer idRelazione;
    private InfermiereDto infermiere;
    private VisitaMedicaDto visitaMedica;
    private CartellaClinicaDto cartellaClinica;
}
