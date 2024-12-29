package com.msmedico.dto.medico.relation;

import com.msmedico.entity.CartellaClinica;
import com.msmedico.entity.visitamedica.RefertoVisitaMedica;
import com.msmedico.entity.visitamedica.VisitaMedica;
import lombok.Data;

@Data
public class VisitaMedicaCartellaDto {
    private Integer idRelazione;
    private CartellaClinica cartellaClinica;
    private VisitaMedica visitaMedica;
    private RefertoVisitaMedica refertoVisitaMedica;
}
