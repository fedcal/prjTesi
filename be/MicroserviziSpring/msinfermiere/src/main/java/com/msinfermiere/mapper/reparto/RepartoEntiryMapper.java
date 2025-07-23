package com.msinfermiere.mapper.reparto;

import com.msinfermiere.dto.infermiere.RepartoDto;
import com.msinfermiere.entity.Reparto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RepartoEntiryMapper {
    RepartoEntiryMapper INSTANCE = Mappers.getMapper(RepartoEntiryMapper.class);

    Reparto toEntity(RepartoDto dto);
    List<Reparto> toEntity(List<RepartoDto> dto);
}
