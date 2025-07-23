package com.msinfermiere.mapper.medico;

import com.msinfermiere.dto.infermiere.MedicoDto;
import com.msinfermiere.entity.Medico;
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
