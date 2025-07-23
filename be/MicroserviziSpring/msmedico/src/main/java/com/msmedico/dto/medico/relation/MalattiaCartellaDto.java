package com.msmedico.dto.medico.relation;

import com.msmedico.dto.medico.CartellaClinicaDto;
import com.msmedico.dto.medico.MalattiaDto;
import lombok.Data;

@Data
public class MalattiaCartellaDto {
    private Integer idRelazione;
    private MalattiaDto malattia;
    private CartellaClinicaDto cartellaClinica;
}
