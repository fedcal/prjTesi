package com.msinfermiere.mapper.relation.medicinalecartella;

import com.msinfermiere.dto.infermiere.relation.MedicinaleCartellaDto;
import com.msinfermiere.entity.relation.MedicinaleCartella;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicinaleCartellaDtoMapper {
    MedicinaleCartellaDtoMapper INSTANCE = Mappers.getMapper(MedicinaleCartellaDtoMapper.class);

    MedicinaleCartellaDto toDto(MedicinaleCartella entity);
    List<MedicinaleCartellaDto> toDto(List<MedicinaleCartella> entity);
}
