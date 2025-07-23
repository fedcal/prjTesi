package com.mspazienti.mapper.relation.visitasottoministrazioneinfermiere;

import com.mspazienti.dto.paziente.relation.VisitaSottoministrazioneInfermiereDto;
import com.mspazienti.entity.relation.VisitaSottoministrazioneInfermiere;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VisitaSottoministrazioneInfermiereEntityMapper {
    VisitaSottoministrazioneInfermiereEntityMapper INSTANCE = Mappers.getMapper(VisitaSottoministrazioneInfermiereEntityMapper.class);

    VisitaSottoministrazioneInfermiere toEntity(VisitaSottoministrazioneInfermiereDto dto);
    List<VisitaSottoministrazioneInfermiere> toEntity(List<VisitaSottoministrazioneInfermiereDto> dto);
}
