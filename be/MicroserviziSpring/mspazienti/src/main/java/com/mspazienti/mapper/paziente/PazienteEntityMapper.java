package com.mspazienti.mapper.paziente;

import com.mspazienti.dto.paziente.PazienteDto;
import com.mspazienti.entity.Paziente;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PazienteEntityMapper {
    PazienteEntityMapper INSTANCE = Mappers.getMapper(PazienteEntityMapper.class);

    Paziente toEntity(PazienteDto dto);
    List<Paziente> toEntity(List<PazienteDto> dto);
}
