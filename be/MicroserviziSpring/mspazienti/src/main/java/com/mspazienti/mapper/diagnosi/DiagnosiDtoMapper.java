package com.mspazienti.mapper.diagnosi;

import com.mspazienti.dto.paziente.DiagnosiDto;
import com.mspazienti.entity.Diagnosi;
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
