package com.msinfermiere.mapper.relation.medicinalecartella;

import com.msinfermiere.dto.infermiere.relation.MedicinaleCartellaDto;
import com.msinfermiere.entity.relation.MedicinaleCartella;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicinaleCartellaEntityMapper {
    MedicinaleCartellaEntityMapper INSTANCE = Mappers.getMapper(MedicinaleCartellaEntityMapper.class);

    MedicinaleCartella toEntity(MedicinaleCartellaDto dto);
    List<MedicinaleCartella> toEntity(List<MedicinaleCartellaDto> dto);
}
