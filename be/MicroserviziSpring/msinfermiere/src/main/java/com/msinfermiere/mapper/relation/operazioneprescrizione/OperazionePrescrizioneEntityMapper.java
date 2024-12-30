package com.msinfermiere.mapper.relation.operazioneprescrizione;

import com.msinfermiere.dto.infermiere.relation.OperazionePrescrizioneDto;
import com.msinfermiere.entity.relation.OperazionePrescrizione;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OperazionePrescrizioneEntityMapper {
    OperazionePrescrizioneEntityMapper INSTANCE = Mappers.getMapper(OperazionePrescrizioneEntityMapper.class);

    OperazionePrescrizione toEntity(OperazionePrescrizioneDto entity);
    List<OperazionePrescrizione> toEntity(List<OperazionePrescrizioneDto> entity);
}
