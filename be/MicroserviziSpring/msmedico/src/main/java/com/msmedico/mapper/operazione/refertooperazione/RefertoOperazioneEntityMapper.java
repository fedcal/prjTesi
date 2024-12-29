package com.msmedico.mapper.operazione.refertooperazione;

import com.msmedico.dto.medico.operazione.RefertoOperazioneDto;
import com.msmedico.entity.operazione.RefertoOperazione;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RefertoOperazioneEntityMapper {
    RefertoOperazioneEntityMapper INSTANCE = Mappers.getMapper(RefertoOperazioneEntityMapper.class);

    RefertoOperazione toEntity(RefertoOperazioneDto refertoOperazione);
    List<RefertoOperazione> toEntity(List<RefertoOperazioneDto> refertoOperazione);
}
