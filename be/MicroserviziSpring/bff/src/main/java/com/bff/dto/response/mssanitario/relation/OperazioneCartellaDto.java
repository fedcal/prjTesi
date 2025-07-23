package com.bff.dto.response.mssanitario.relation;

import com.bff.dto.response.mssanitario.CartellaClinicaDto;
import com.bff.dto.response.mssanitario.MedicoDto;
import com.bff.dto.response.mssanitario.operazione.OperazioneMedicaDto;
import lombok.Data;

@Data
public class OperazioneCartellaDto {
    private Integer idRelazione;
    private OperazioneMedicaDto operazioneMedica;
    private CartellaClinicaDto cartellaClinica;
    private MedicoDto medico;
}
