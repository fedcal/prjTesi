package com.msmedico.mapper.relation.malattiacartella;

import com.msmedico.dto.medico.relation.MalattiaCartellaDto;
import com.msmedico.entity.relation.MalattiaCartella;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MalattiaCartellaDtoMapper {
    MalattiaCartellaDtoMapper INSTANCE = Mappers.getMapper(MalattiaCartellaDtoMapper.class);

    MalattiaCartellaDto toDto(MalattiaCartella entity);
    List<MalattiaCartellaDto> toDto(List<MalattiaCartella> entity);
}
