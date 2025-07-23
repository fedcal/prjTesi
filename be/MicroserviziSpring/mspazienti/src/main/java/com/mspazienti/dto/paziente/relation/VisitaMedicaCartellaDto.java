package com.mspazienti.dto.paziente.relation;

import com.mspazienti.dto.paziente.CartellaClinicaDto;
import com.mspazienti.dto.paziente.visitamedica.RefertoVisitaMedicaDto;
import com.mspazienti.dto.paziente.visitamedica.VisitaMedicaDto;
import lombok.Data;

@Data
public class VisitaMedicaCartellaDto {
    private Integer idRelazione;
    private CartellaClinicaDto cartellaClinica;
    private VisitaMedicaDto visitaMedica;
    private RefertoVisitaMedicaDto refertoVisitaMedica;
}
