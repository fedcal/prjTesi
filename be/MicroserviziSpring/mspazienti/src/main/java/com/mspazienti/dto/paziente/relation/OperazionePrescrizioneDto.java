package com.mspazienti.dto.paziente.relation;

import com.mspazienti.dto.paziente.CartellaClinicaDto;
import com.mspazienti.dto.paziente.MedicoDto;
import com.mspazienti.dto.paziente.operazione.OperazioneMedicaDto;
import lombok.Data;

@Data
public class OperazionePrescrizioneDto {
    private Integer idRelazione;
    private OperazioneMedicaDto operazioneMedica;
    private CartellaClinicaDto cartellaClinica;
    private MedicoDto medico;
}
