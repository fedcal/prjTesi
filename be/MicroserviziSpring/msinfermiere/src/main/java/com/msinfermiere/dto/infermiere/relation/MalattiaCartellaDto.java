package com.msinfermiere.dto.infermiere.relation;

import com.msinfermiere.dto.infermiere.CartellaClinicaDto;
import com.msinfermiere.dto.infermiere.MalattiaDto;
import lombok.Data;

@Data
public class MalattiaCartellaDto {
    private Integer idRelazione;
    private MalattiaDto malattia;
    private CartellaClinicaDto cartellaClinica;
}
