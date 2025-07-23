package com.mspazienti.mapper.visitamedica.refertovisitamedica;

import com.mspazienti.dto.paziente.visitamedica.RefertoVisitaMedicaDto;
import com.mspazienti.entity.visitamedica.RefertoVisitaMedica;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RefertoVisitaMedicaEntityMapper {
    RefertoVisitaMedicaEntityMapper INSTANCE = Mappers.getMapper(RefertoVisitaMedicaEntityMapper.class);

    RefertoVisitaMedica toEntity(RefertoVisitaMedicaDto dto);
    List<RefertoVisitaMedica> toEntity(List<RefertoVisitaMedicaDto> dto);
}
