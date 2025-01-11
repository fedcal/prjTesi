package com.bff.dto.response.mspaziente.relation;

import com.bff.dto.response.mspaziente.CartellaClinicaDto;
import com.bff.dto.response.mspaziente.visitamedica.RefertoVisitaMedicaDto;
import com.bff.dto.response.mspaziente.visitamedica.VisitaMedicaDto;
import lombok.Data;

@Data
public class VisitaMedicaCartellaDto {
    private Integer idRelazione;
    private CartellaClinicaDto cartellaClinica;
    private VisitaMedicaDto visitaMedica;
    private RefertoVisitaMedicaDto refertoVisitaMedica;
}
