package com.msinfermiere.mapper.diagnosi;

import com.msinfermiere.dto.infermiere.DiagnosiDto;
import com.msinfermiere.entity.Diagnosi;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiagnosiDtoMapper {
    DiagnosiDtoMapper INSTANCE = Mappers.getMapper(DiagnosiDtoMapper.class);

    DiagnosiDto toDto(Diagnosi entity);
    List<DiagnosiDto> toDto(List<Diagnosi> entity);
}
