package com.mspazienti.mapper.operazione.operazionemedica;

import com.mspazienti.dto.paziente.operazione.OperazioneMedicaDto;
import com.mspazienti.entity.operazione.OperazioneMedica;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OperazioneMedicaDtoMapper {
    OperazioneMedicaDtoMapper INSTANCE = Mappers.getMapper(OperazioneMedicaDtoMapper.class);

    OperazioneMedicaDto toDto(OperazioneMedica entity);
    List<OperazioneMedicaDto> toDto(List<OperazioneMedica> entity);
}
