package com.mspazienti.mapper.malattia;

import com.mspazienti.dto.paziente.MalattiaDto;
import com.mspazienti.entity.Malattia;
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
