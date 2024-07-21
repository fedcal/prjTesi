package com.filemanagement.mapper.cartelle;

import com.filemanagement.dto.CartelleDto;
import com.filemanagement.entity.Cartelle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CartelleEntityMapper {
    CartelleEntityMapper INSTANCE = Mappers.getMapper(CartelleEntityMapper.class);

    Cartelle toEntity(CartelleDto entity);
    List<Cartelle> toEntities(List<CartelleDto> entity);
}
