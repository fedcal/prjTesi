package com.msmedico.mapper.relation.medicinalecartella;

import com.msmedico.dto.medico.relation.MedicinaleCartellaDto;
import com.msmedico.entity.relation.MedicinaleCartella;
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
