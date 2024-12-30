package com.msinfermiere.mapper.relation.operazionecartella;

import com.msinfermiere.dto.infermiere.relation.OperazioneCartellaDto;
import com.msinfermiere.entity.relation.OperazioneCartella;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OperazioneCartellaEntityMapper {
    OperazioneCartellaEntityMapper INSTANCE = Mappers.getMapper(OperazioneCartellaEntityMapper.class);

    OperazioneCartella toEntity(OperazioneCartellaDto dto);
    List<OperazioneCartella> toEntity(List<OperazioneCartellaDto> dto);
}
