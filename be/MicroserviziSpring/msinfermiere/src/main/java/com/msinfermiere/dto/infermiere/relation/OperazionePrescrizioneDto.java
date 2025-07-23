package com.msinfermiere.dto.infermiere.relation;

import com.msinfermiere.dto.infermiere.CartellaClinicaDto;
import com.msinfermiere.dto.infermiere.MedicoDto;
import com.msinfermiere.dto.infermiere.operazione.OperazioneMedicaDto;
import lombok.Data;

@Data
public class OperazionePrescrizioneDto {
    private Integer idRelazione;
    private OperazioneMedicaDto operazioneMedica;
    private CartellaClinicaDto cartellaClinica;
    private MedicoDto medico;
}
