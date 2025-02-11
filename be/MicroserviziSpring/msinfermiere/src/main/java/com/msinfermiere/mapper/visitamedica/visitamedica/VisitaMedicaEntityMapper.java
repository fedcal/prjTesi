package com.msinfermiere.mapper.visitamedica.visitamedica;

import com.msinfermiere.dto.infermiere.visitamedica.VisitaMedicaDto;
import com.msinfermiere.entity.visitamedica.VisitaMedica;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VisitaMedicaEntityMapper {
    VisitaMedicaEntityMapper INSTANCE = Mappers.getMapper(VisitaMedicaEntityMapper.class);

    VisitaMedica toEntity(VisitaMedicaDto dto);
    List<VisitaMedica> toEntity(List<VisitaMedicaDto> dto);
}
