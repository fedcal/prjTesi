package com.mspazienti.mapper.infermiere;

import com.mspazienti.dto.paziente.InfermiereDto;
import com.mspazienti.entity.Infermiere;
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
