package com.mspazienti.mapper.reparto;

import com.mspazienti.dto.paziente.RepartoDto;
import com.mspazienti.entity.Reparto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RepartoEntiryMapper {
    RepartoEntiryMapper INSTANCE = Mappers.getMapper(RepartoEntiryMapper.class);

    Reparto toEntity(RepartoDto dto);
    List<Reparto> toEntity(List<RepartoDto> dto);
}
