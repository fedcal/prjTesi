package com.msinfermiere.mapper.paziente;

import com.msinfermiere.dto.infermiere.PazienteDto;
import com.msinfermiere.entity.Paziente;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PazienteDtoMapper {
    PazienteDtoMapper INSTANCE = Mappers.getMapper(PazienteDtoMapper.class);

    PazienteDto toDto(Paziente entity);
    List<PazienteDto> toDto(List<Paziente> entity);
}
