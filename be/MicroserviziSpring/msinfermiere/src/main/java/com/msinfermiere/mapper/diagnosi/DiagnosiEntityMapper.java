package com.msinfermiere.mapper.diagnosi;

import com.msinfermiere.dto.infermiere.DiagnosiDto;
import com.msinfermiere.entity.Diagnosi;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiagnosiEntityMapper {
    DiagnosiEntityMapper INSTANCE = Mappers.getMapper(DiagnosiEntityMapper.class);

    Diagnosi toEntity(DiagnosiDto dto);
    List<Diagnosi> toEntity(List<DiagnosiDto> dto);
}
