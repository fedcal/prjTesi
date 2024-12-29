package com.msmedico.mapper.diagnosi;

import com.msmedico.dto.medico.DiagnosiDto;
import com.msmedico.entity.Diagnosi;
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
