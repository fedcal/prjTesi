package com.mspazienti.mapper.relation.medicinalecartella;

import com.mspazienti.dto.paziente.relation.MedicinaleCartellaDto;
import com.mspazienti.entity.relation.MedicinaleCartella;
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
