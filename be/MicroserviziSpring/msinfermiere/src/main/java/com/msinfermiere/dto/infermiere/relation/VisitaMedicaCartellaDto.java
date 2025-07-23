package com.msinfermiere.dto.infermiere.relation;

import com.msinfermiere.entity.CartellaClinica;
import com.msinfermiere.entity.visitamedica.RefertoVisitaMedica;
import com.msinfermiere.entity.visitamedica.VisitaMedica;
import lombok.Data;

@Data
public class VisitaMedicaCartellaDto {
    private Integer idRelazione;
    private CartellaClinica cartellaClinica;
    private VisitaMedica visitaMedica;
    private RefertoVisitaMedica refertoVisitaMedica;
}
