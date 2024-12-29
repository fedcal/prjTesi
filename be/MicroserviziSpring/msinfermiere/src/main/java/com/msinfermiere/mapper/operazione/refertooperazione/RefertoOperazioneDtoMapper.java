package com.msinfermiere.mapper.operazione.refertooperazione;

import com.msinfermiere.dto.infermiere.operazione.RefertoOperazioneDto;
import com.msinfermiere.entity.operazione.RefertoOperazione;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RefertoOperazioneDtoMapper {
    RefertoOperazioneDtoMapper INSTANCE = Mappers.getMapper(RefertoOperazioneDtoMapper.class);

    RefertoOperazioneDto toDto(RefertoOperazione refertoOperazione);
    List<RefertoOperazioneDto> toDto(List<RefertoOperazione> refertoOperazione);
}
