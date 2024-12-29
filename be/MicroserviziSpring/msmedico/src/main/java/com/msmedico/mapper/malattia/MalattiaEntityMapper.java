package com.msmedico.mapper.malattia;

import com.msmedico.dto.medico.MalattiaDto;
import com.msmedico.entity.Malattia;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MalattiaEntityMapper {
    MalattiaEntityMapper INSTANCE = Mappers.getMapper(MalattiaEntityMapper.class);

    Malattia toEntity(MalattiaDto dto);
    List<Malattia> toEntity(List<MalattiaDto> dto);
}
