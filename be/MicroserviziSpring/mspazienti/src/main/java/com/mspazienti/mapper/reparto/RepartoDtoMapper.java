package com.mspazienti.mapper.reparto;

import com.mspazienti.dto.paziente.RepartoDto;
import com.mspazienti.entity.Reparto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RepartoDtoMapper {
    RepartoDtoMapper INSTANCE = Mappers.getMapper(RepartoDtoMapper.class);

    RepartoDto toDto(Reparto entity);
    List<RepartoDto> toDto(List<Reparto> entity);
}
