package com.msinfermiere.mapper.visitamedica.visitamedica;

import com.msinfermiere.dto.infermiere.visitamedica.VisitaMedicaDto;
import com.msinfermiere.entity.visitamedica.VisitaMedica;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VisitaMedicaDtoMapper {
    VisitaMedicaDtoMapper INSTANCE = Mappers.getMapper(VisitaMedicaDtoMapper.class);

    VisitaMedicaDto toDto(VisitaMedica entity);
    List<VisitaMedicaDto> toDto(List<VisitaMedica> entity);
}
