package com.systemmanagement.mapper.cartelle;

import com.systemmanagement.dto.CartelleDto;
import com.systemmanagement.entity.Cartelle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CartelleEntityMapper {
    CartelleEntityMapper INSTANCE = Mappers.getMapper(CartelleEntityMapper.class);

    Cartelle toEntity(CartelleDto entity);
    List<Cartelle> toEntities(List<CartelleDto> entity);
}
