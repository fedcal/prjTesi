package com.bff.dto.response.mspaziente.relation;

import com.bff.dto.response.mspaziente.CartellaClinicaDto;
import com.bff.dto.response.mspaziente.MalattiaDto;
import lombok.Data;

@Data
public class MalattiaCartellaDto {
    private Integer idRelazione;
    private MalattiaDto malattia;
    private CartellaClinicaDto cartellaClinica;
}
