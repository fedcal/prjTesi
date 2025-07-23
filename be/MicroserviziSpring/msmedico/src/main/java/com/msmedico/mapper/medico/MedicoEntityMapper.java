package com.msmedico.mapper.medico;

import com.msmedico.dto.medico.MedicoDto;
import com.msmedico.entity.Medico;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicoEntityMapper {
    MedicoEntityMapper INSTANCE = Mappers.getMapper(MedicoEntityMapper.class);

    Medico toEntity(MedicoDto dto);
    List<Medico> toEntity(List<MedicoDto> dto);
}
