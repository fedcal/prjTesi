package com.msinfermiere.mapper.visitamedica.refertovisitamedica;

import com.msinfermiere.dto.infermiere.visitamedica.RefertoVisitaMedicaDto;
import com.msinfermiere.entity.visitamedica.RefertoVisitaMedica;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RefertoVisitaMedicaDtoMapper {
    RefertoVisitaMedicaDtoMapper INSTANCE = Mappers.getMapper(RefertoVisitaMedicaDtoMapper.class);

    RefertoVisitaMedicaDto toDto(RefertoVisitaMedica entity);
    List<RefertoVisitaMedicaDto> toDto(List<RefertoVisitaMedica> entity);
}
