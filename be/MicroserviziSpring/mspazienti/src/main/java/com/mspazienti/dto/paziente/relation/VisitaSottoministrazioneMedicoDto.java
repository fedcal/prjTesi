package com.mspazienti.dto.paziente.relation;


import com.mspazienti.dto.paziente.CartellaClinicaDto;
import com.mspazienti.dto.paziente.MedicoDto;
import com.mspazienti.dto.paziente.visitamedica.VisitaMedicaDto;
import lombok.Data;

@Data
public class VisitaSottoministrazioneMedicoDto {
    private Integer idRelazione;
    private MedicoDto medico;
    private VisitaMedicaDto visitaMedica;
    private CartellaClinicaDto cartellaClinica;
}
