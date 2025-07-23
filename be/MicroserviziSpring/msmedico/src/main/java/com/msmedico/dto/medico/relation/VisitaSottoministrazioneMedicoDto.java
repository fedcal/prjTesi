package com.msmedico.dto.medico.relation;

import com.msmedico.dto.medico.CartellaClinicaDto;
import com.msmedico.dto.medico.MedicoDto;
import com.msmedico.dto.medico.visitamedica.VisitaMedicaDto;
import lombok.Data;

@Data
public class VisitaSottoministrazioneMedicoDto {
    private Integer idRelazione;
    private MedicoDto medico;
    private VisitaMedicaDto visitaMedica;
    private CartellaClinicaDto cartellaClinica;
}
