package com.mspazienti.dto.paziente.relation;

import com.mspazienti.entity.CartellaClinica;
import com.mspazienti.entity.visitamedica.RefertoVisitaMedica;
import com.mspazienti.entity.visitamedica.VisitaMedica;
import lombok.Data;

@Data
public class VisitaMedicaCartellaDto {
    private Integer idRelazione;
    private CartellaClinica cartellaClinica;
    private VisitaMedica visitaMedica;
    private RefertoVisitaMedica refertoVisitaMedica;
}
