package com.msinfermiere.dto.infermiere.relation;

import com.msinfermiere.dto.infermiere.CartellaClinicaDto;
import com.msinfermiere.dto.infermiere.MedicoDto;
import com.msinfermiere.dto.infermiere.visitamedica.VisitaMedicaDto;
import lombok.Data;

@Data
public class VisitaPrescrizioneDto {
    private Integer idRelazione;
    private MedicoDto medico;
    private VisitaMedicaDto visitaMedica;
    private CartellaClinicaDto cartellaClinica;
}
