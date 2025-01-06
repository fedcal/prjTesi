package com.bff.dto.response.mspaziente;

import lombok.Data;

@Data
public class VisitaPrescrizioneDto {
    private Integer idRelazione;
    private MedicoDto medico;
    private VisitaMedicaDto visitaMedica;
    private CartellaClinicaDto cartellaClinica;
}
