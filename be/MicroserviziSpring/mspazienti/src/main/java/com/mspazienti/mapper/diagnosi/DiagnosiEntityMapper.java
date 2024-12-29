package com.mspazienti.mapper.diagnosi;

import com.mspazienti.dto.paziente.DiagnosiDto;
import com.mspazienti.entity.Diagnosi;
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
