package com.mspazienti.mapper.cartellaclinica;

import com.mspazienti.dto.paziente.CartellaClinicaDto;
import com.mspazienti.entity.CartellaClinica;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartellaClinicaEntityMapper {
    CartellaClinicaEntityMapper INSTANCE = Mappers.getMapper(CartellaClinicaEntityMapper.class);

    CartellaClinica toEntity(CartellaClinicaDto dto);
    List<CartellaClinica> toEntity(List<CartellaClinicaDto> dto);
}
