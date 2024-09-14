package com.systemmanagement.mapper.documenti;

import com.systemmanagement.dto.DocumentiDto;
import com.systemmanagement.entity.Documenti;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DocumentiDtoMapper {
    DocumentiDtoMapper INSTANCE = Mappers.getMapper(DocumentiDtoMapper.class);

    DocumentiDto toDto(Documenti entity);
    List<DocumentiDto> toDtos(List<Documenti> entity);
}
