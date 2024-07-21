package com.filemanagement.mapper.documenti;

import com.filemanagement.dto.DocumentiDto;
import com.filemanagement.entity.Documenti;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DocumentiEntityMapper {
    DocumentiDtoMapper INSTANCE = Mappers.getMapper(DocumentiDtoMapper.class);

    Documenti toEntity(DocumentiDto entity);
    List<Documenti> toEntities(List<DocumentiDto> entity);
}
