package com.mspazienti.mapper.relation.visitaprescrizione;

import com.mspazienti.dto.paziente.relation.VisitaPrescrizioneDto;
import com.mspazienti.entity.relation.VisitaPrescrizione;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VisitaPrescrizioneDtoMapper {
    VisitaPrescrizioneDtoMapper INSTANCE = Mappers.getMapper(VisitaPrescrizioneDtoMapper.class);

    VisitaPrescrizioneDto toDto(VisitaPrescrizione visitaPrescrizione);
    List<VisitaPrescrizioneDto> toDto(List<VisitaPrescrizione> visitaPrescrizione);
}
