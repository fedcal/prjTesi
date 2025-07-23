package com.mspazienti.mapper.medico;

import com.mspazienti.dto.paziente.MedicoDto;
import com.mspazienti.entity.Medico;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicoDtoMapper {
    MedicoDtoMapper INSTANCE = Mappers.getMapper(MedicoDtoMapper.class);

    MedicoDto toDto(Medico entity);
    List<MedicoDto> toDto(List<Medico> entity);
}
