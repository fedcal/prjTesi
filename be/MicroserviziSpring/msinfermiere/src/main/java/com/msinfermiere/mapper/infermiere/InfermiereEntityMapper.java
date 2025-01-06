package com.msinfermiere.mapper.infermiere;

import com.msinfermiere.dto.infermiere.InfermiereDto;
import com.msinfermiere.dto.request.gestioneinfermiere.AddInfermiereDto;
import com.msinfermiere.entity.Infermiere;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InfermiereEntityMapper {
    InfermiereEntityMapper INSTANCE = Mappers.getMapper(InfermiereEntityMapper.class);

    Infermiere toDto(InfermiereDto entity);
    List<Infermiere> toDto(List<InfermiereDto> entity);

    Infermiere fromRequest(AddInfermiereDto addInfermiereDto);
}
