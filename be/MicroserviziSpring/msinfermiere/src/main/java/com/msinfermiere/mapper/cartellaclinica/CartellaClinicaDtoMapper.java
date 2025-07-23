package com.msinfermiere.mapper.cartellaclinica;

import com.msinfermiere.dto.infermiere.CartellaClinicaDto;
import com.msinfermiere.entity.CartellaClinica;
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
