package com.msmedico.dto.medico.relation;

import com.msmedico.dto.medico.CartellaClinicaDto;
import com.msmedico.dto.medico.MedicoDto;
import com.msmedico.dto.medico.operazione.OperazioneMedicaDto;
import lombok.Data;

@Data
public class OperazionePrescrizioneDto {
    private Integer idRelazione;
    private OperazioneMedicaDto operazioneMedica;
    private CartellaClinicaDto cartellaClinica;
    private MedicoDto medico;
}
