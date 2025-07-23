package com.msinfermiere.mapper.cartellaclinica;

import com.msinfermiere.dto.infermiere.CartellaClinicaDto;
import com.msinfermiere.dto.request.gestionepaziente.AddCartellaClinicaDto;
import com.msinfermiere.entity.CartellaClinica;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartellaClinicaEntityMapper {
    CartellaClinicaEntityMapper INSTANCE = Mappers.getMapper(CartellaClinicaEntityMapper.class);

    CartellaClinica toEntity(CartellaClinicaDto dto);
    List<CartellaClinica> toEntity(List<CartellaClinicaDto> dto);

    CartellaClinica toEntity(AddCartellaClinicaDto addCartellaClinicaDto);
}
