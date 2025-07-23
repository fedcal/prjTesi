package com.msinfermiere.mapper.relation.visitamedicacartella;

import com.msinfermiere.dto.infermiere.relation.VisitaMedicaCartellaDto;
import com.msinfermiere.entity.relation.VisitaMedicaCartella;
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
