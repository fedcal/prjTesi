package com.mspazienti.mapper.relation.visitamedicacartella;

import com.mspazienti.dto.paziente.relation.VisitaMedicaCartellaDto;
import com.mspazienti.entity.relation.VisitaMedicaCartella;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VisitaMedicaCartellaDtoMapper {
    VisitaMedicaCartellaDtoMapper INSTANCE = Mappers.getMapper(VisitaMedicaCartellaDtoMapper.class);

    VisitaMedicaCartellaDto toDto(VisitaMedicaCartella visitaMedicaCartella);
    List<VisitaMedicaCartellaDto> toDto(List<VisitaMedicaCartella> visitaMedicaCartella);
}
