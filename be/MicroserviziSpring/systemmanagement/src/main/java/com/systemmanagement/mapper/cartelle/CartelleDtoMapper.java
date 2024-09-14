package com.systemmanagement.mapper.cartelle;

import com.systemmanagement.dto.CartelleDto;
import com.systemmanagement.entity.Cartelle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CartelleDtoMapper {
    CartelleDtoMapper INSTANCE = Mappers.getMapper(CartelleDtoMapper.class);

    CartelleDto toDto(Cartelle entity);
    List<CartelleDto> toDtos(List<Cartelle> entity);
}
