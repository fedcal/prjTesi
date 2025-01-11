package com.bff.dto.response.mssanitario.relation;

import com.bff.dto.response.mssanitario.CartellaClinicaDto;
import com.bff.dto.response.mssanitario.InfermiereDto;
import com.bff.dto.response.mssanitario.visitamedica.VisitaMedicaDto;
import lombok.Data;

@Data
public class VisitaSottoministrazioneInfermiereDto {
    private Integer idRelazione;
    private InfermiereDto infermiere;
    private VisitaMedicaDto visitaMedica;
    private CartellaClinicaDto cartellaClinica;
}
