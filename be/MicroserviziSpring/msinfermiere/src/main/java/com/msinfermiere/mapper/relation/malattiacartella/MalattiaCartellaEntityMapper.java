package com.msinfermiere.mapper.relation.malattiacartella;

import com.msinfermiere.dto.infermiere.relation.MalattiaCartellaDto;
import com.msinfermiere.entity.relation.MalattiaCartella;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MalattiaCartellaEntityMapper {
    MalattiaCartellaEntityMapper INSTANCE = Mappers.getMapper(MalattiaCartellaEntityMapper.class);

    MalattiaCartella toEntity(MalattiaCartellaDto dto);
    List<MalattiaCartella> toEntity(List<MalattiaCartellaDto> dto);
}
