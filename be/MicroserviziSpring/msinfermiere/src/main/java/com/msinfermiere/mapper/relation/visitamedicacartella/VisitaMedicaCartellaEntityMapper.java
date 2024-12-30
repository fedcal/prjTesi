package com.msinfermiere.mapper.relation.visitamedicacartella;

import com.msinfermiere.dto.infermiere.relation.VisitaMedicaCartellaDto;
import com.msinfermiere.entity.relation.VisitaMedicaCartella;
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
