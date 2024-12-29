package com.mspazienti.mapper.medicinale;

import com.mspazienti.dto.paziente.MedicinaleDto;
import com.mspazienti.entity.Medicinale;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicinaleEntityMapper {
    MedicinaleEntityMapper INSTANCE = Mappers.getMapper(MedicinaleEntityMapper.class);

    Medicinale toEntity(MedicinaleDto dto);
    List<Medicinale> toEntity(List<MedicinaleDto> dto);
}
