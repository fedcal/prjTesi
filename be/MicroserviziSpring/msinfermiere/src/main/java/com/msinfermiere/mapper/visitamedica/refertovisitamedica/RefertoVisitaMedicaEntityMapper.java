package com.msinfermiere.mapper.visitamedica.refertovisitamedica;

import com.msinfermiere.dto.infermiere.visitamedica.RefertoVisitaMedicaDto;
import com.msinfermiere.entity.visitamedica.RefertoVisitaMedica;
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
