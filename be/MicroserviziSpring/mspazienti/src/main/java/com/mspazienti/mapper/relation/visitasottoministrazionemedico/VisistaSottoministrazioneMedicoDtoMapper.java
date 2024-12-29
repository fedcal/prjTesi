package com.mspazienti.mapper.relation.visitasottoministrazionemedico;

import com.mspazienti.dto.paziente.relation.VisitaSottoministrazioneMedicoDto;
import com.mspazienti.entity.relation.VisitaSottoministrazioneMedico;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VisistaSottoministrazioneMedicoDtoMapper {
    VisistaSottoministrazioneMedicoDtoMapper INSTANCE = Mappers.getMapper(VisistaSottoministrazioneMedicoDtoMapper.class);

    VisitaSottoministrazioneMedicoDto toDto(VisitaSottoministrazioneMedico entity);
    List<VisitaSottoministrazioneMedicoDto> toDto(List<VisitaSottoministrazioneMedico> entity);
}
