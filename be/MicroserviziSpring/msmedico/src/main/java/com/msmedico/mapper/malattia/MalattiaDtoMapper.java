package com.msmedico.mapper.malattia;

import com.msmedico.dto.medico.MalattiaDto;
import com.msmedico.entity.Malattia;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MalattiaDtoMapper {
    MalattiaDtoMapper INSTANCE = Mappers.getMapper(MalattiaDtoMapper.class);

    MalattiaDto toDto(Malattia entity);
    List<MalattiaDto> toDto(List<Malattia> entity);
}
