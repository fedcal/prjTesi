package com.msinfermiere.mapper.relation.visitaprescrizione;

import com.msinfermiere.dto.infermiere.relation.VisitaPrescrizioneDto;
import com.msinfermiere.entity.relation.VisitaPrescrizione;
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
