package com.msinfermiere.mapper.infermiere;

import com.msinfermiere.dto.infermiere.InfermiereDto;
import com.msinfermiere.entity.Infermiere;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InfermiereDtoMapper {
    InfermiereDtoMapper INSTANCE = Mappers.getMapper(InfermiereDtoMapper.class);

    InfermiereDto toDto(Infermiere entity);
    List<InfermiereDto> toDto(List<Infermiere> entity);
}
