package com.mspazienti.dto.paziente.relation;

import com.mspazienti.dto.paziente.CartellaClinicaDto;
import com.mspazienti.dto.paziente.InfermiereDto;
import com.mspazienti.dto.paziente.visitamedica.VisitaMedicaDto;
import lombok.Data;

@Data
public class VisitaSottoministrazioneInfermiereDto {
    private Integer idRelazione;
    private InfermiereDto infermiere;
    private VisitaMedicaDto visitaMedica;
    private CartellaClinicaDto cartellaClinica;
}
