package com.systemmanagement.mapper.ragbotpdf;

import com.systemmanagement.dto.RagBotPdfDto;
import com.systemmanagement.entity.RagBotPdf;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RagBotPdfDtoMapper {
    RagBotPdfDtoMapper INSTANCE = Mappers.getMapper(RagBotPdfDtoMapper.class);

    RagBotPdfDto toDto(RagBotPdf entity);
    List<RagBotPdfDto> toDtos(List<RagBotPdf> entity);
}
