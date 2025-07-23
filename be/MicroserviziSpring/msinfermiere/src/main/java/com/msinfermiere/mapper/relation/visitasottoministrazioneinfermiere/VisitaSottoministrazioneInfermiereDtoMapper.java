package com.msinfermiere.mapper.relation.visitasottoministrazioneinfermiere;

import com.msinfermiere.dto.infermiere.relation.VisitaSottoministrazioneInfermiereDto;
import com.msinfermiere.entity.relation.VisitaSottoministrazioneInfermiere;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VisitaSottoministrazioneInfermiereDtoMapper {
    VisitaSottoministrazioneInfermiereDtoMapper INSTANCE = Mappers.getMapper(VisitaSottoministrazioneInfermiereDtoMapper.class);

    VisitaSottoministrazioneInfermiereDto toDto(VisitaSottoministrazioneInfermiere entity);
    List<VisitaSottoministrazioneInfermiereDto> toDto(List<VisitaSottoministrazioneInfermiere> entity);
}
