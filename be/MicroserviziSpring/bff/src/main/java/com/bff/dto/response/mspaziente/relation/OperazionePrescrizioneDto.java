package com.bff.dto.response.mspaziente.relation;

import com.bff.dto.response.mspaziente.CartellaClinicaDto;
import com.bff.dto.response.mspaziente.MedicoDto;
import com.bff.dto.response.mspaziente.operazione.OperazioneMedicaDto;
import lombok.Data;

@Data
public class OperazionePrescrizioneDto {
    private Integer idRelazione;
    private OperazioneMedicaDto operazioneMedica;
    private CartellaClinicaDto cartellaClinica;
    private MedicoDto medico;
}
