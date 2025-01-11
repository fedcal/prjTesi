package com.bff.dto.response.mssanitario.relation;

import com.bff.dto.response.mssanitario.CartellaClinicaDto;
import com.bff.dto.response.mssanitario.MedicoDto;
import com.bff.dto.response.mssanitario.visitamedica.VisitaMedicaDto;
import lombok.Data;

@Data
public class VisitaSottoministrazioneMedicoDto {
    private Integer idRelazione;
    private MedicoDto medico;
    private VisitaMedicaDto visitaMedica;
    private CartellaClinicaDto cartellaClinica;
}
