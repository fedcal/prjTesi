package com.msinfermiere.mapper.relation.visitaprescrizione;

import com.msinfermiere.dto.infermiere.relation.VisitaPrescrizioneDto;
import com.msinfermiere.entity.relation.VisitaPrescrizione;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VisitaPrescrizioneEntityMapper {
    VisitaPrescrizioneEntityMapper INSTANCE = Mappers.getMapper(VisitaPrescrizioneEntityMapper.class);

    VisitaPrescrizione toEntity(VisitaPrescrizioneDto dto);
    List<VisitaPrescrizione> toEntity(List<VisitaPrescrizioneDto> dto);
}
