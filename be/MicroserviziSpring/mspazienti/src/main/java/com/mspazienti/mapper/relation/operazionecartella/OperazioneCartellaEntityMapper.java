package com.mspazienti.mapper.relation.operazionecartella;

import com.mspazienti.dto.paziente.relation.OperazioneCartellaDto;
import com.mspazienti.entity.relation.OperazioneCartella;
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
