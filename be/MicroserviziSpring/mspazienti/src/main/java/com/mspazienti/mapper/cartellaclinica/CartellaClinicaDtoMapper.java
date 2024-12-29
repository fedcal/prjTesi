package com.mspazienti.mapper.cartellaclinica;

import com.mspazienti.dto.paziente.CartellaClinicaDto;
import com.mspazienti.entity.CartellaClinica;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartellaClinicaDtoMapper {
    CartellaClinicaDtoMapper INSTANCE = Mappers.getMapper(CartellaClinicaDtoMapper.class);

    CartellaClinicaDto toDto(CartellaClinica entity);
    List<CartellaClinicaDto> toDto(List<CartellaClinica> entity);
}
