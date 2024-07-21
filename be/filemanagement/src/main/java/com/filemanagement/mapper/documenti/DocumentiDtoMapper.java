package com.filemanagement.mapper.documenti;

import com.filemanagement.dto.DocumentiDto;
import com.filemanagement.entity.Documenti;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DocumentiDtoMapper {
    DocumentiDtoMapper INSTANCE = Mappers.getMapper(DocumentiDtoMapper.class);

    DocumentiDto toDto(Documenti entity);
    List<DocumentiDto> toDtos(List<Documenti> entity);
}
