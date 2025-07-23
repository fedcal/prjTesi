package com.mspazienti.mapper.infermiere;

import com.mspazienti.dto.paziente.InfermiereDto;
import com.mspazienti.entity.Infermiere;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InfermiereEntityMapper {
    InfermiereEntityMapper INSTANCE = Mappers.getMapper(InfermiereEntityMapper.class);

    Infermiere toDto(InfermiereDto entity);
    List<Infermiere> toDto(List<InfermiereDto> entity);
}
