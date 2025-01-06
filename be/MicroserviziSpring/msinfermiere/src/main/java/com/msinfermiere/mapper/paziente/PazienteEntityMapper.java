package com.msinfermiere.mapper.paziente;

import com.msinfermiere.dto.infermiere.PazienteDto;
import com.msinfermiere.dto.request.gestionepaziente.AddPazientereDto;
import com.msinfermiere.entity.CartellaClinica;
import com.msinfermiere.entity.ContattoRiferimento;
import com.msinfermiere.entity.Paziente;
import com.msinfermiere.entity.Reparto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PazienteEntityMapper {
    PazienteEntityMapper INSTANCE = Mappers.getMapper(PazienteEntityMapper.class);

    Paziente toEntity(PazienteDto dto);
    List<Paziente> toEntity(List<PazienteDto> dto);

    Paziente toEntity(AddPazientereDto addPazientereDto);
}
