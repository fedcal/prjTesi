package com.bff.dto.response.mspaziente.relation;

import com.bff.dto.response.mspaziente.CartellaClinicaDto;
import com.bff.dto.response.mspaziente.InfermiereDto;
import com.bff.dto.response.mspaziente.visitamedica.VisitaMedicaDto;
import lombok.Data;

@Data
public class VisitaSottoministrazioneInfermiereDto {
    private Integer idRelazione;
    private InfermiereDto infermiere;
    private VisitaMedicaDto visitaMedica;
    private CartellaClinicaDto cartellaClinica;
}
