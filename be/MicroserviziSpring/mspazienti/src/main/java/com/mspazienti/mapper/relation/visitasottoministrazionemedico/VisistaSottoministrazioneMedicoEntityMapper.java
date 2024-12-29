package com.mspazienti.mapper.relation.visitasottoministrazionemedico;

import com.mspazienti.dto.paziente.relation.VisitaSottoministrazioneMedicoDto;
import com.mspazienti.entity.relation.VisitaSottoministrazioneMedico;
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
