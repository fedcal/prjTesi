package com.mspazienti.mapper.relation.visitamedicacartella;

import com.mspazienti.dto.paziente.relation.VisitaMedicaCartellaDto;
import com.mspazienti.entity.relation.VisitaMedicaCartella;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VisitaMedicaCartellaEntityMapper {
    VisitaMedicaCartellaEntityMapper INSTANCE = Mappers.getMapper(VisitaMedicaCartellaEntityMapper.class);

    VisitaMedicaCartella toEntity(VisitaMedicaCartellaDto dto);
    List<VisitaMedicaCartella> toEntity(List<VisitaMedicaCartellaDto> dto);
}
