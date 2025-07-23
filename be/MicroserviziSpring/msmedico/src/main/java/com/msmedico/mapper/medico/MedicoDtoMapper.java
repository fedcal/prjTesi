package com.msmedico.mapper.medico;

import com.msmedico.dto.medico.MedicoDto;
import com.msmedico.entity.Medico;
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
