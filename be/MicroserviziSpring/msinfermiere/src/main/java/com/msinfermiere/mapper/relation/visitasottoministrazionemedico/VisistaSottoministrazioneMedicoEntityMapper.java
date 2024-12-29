package com.msinfermiere.mapper.relation.visitasottoministrazionemedico;

import com.msinfermiere.dto.infermiere.relation.VisitaSottoministrazioneMedicoDto;
import com.msinfermiere.entity.relation.VisitaSottoministrazioneMedico;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VisistaSottoministrazioneMedicoEntityMapper {
    VisistaSottoministrazioneMedicoEntityMapper INSTANCE = Mappers.getMapper(VisistaSottoministrazioneMedicoEntityMapper.class);

    VisitaSottoministrazioneMedico toEntity(VisitaSottoministrazioneMedicoDto dto);
    List<VisitaSottoministrazioneMedico> toEntity(List<VisitaSottoministrazioneMedicoDto> dto);
}
