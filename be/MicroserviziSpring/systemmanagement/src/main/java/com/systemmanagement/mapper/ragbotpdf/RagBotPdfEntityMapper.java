package com.systemmanagement.mapper.ragbotpdf;


import com.systemmanagement.dto.RagBotPdfDto;
import com.systemmanagement.entity.RagBotPdf;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RagBotPdfEntityMapper {
    RagBotPdfEntityMapper INSTANCE = Mappers.getMapper(RagBotPdfEntityMapper.class);

    RagBotPdf toEntity(RagBotPdfDto entity);
    List<RagBotPdf> toEntities(List<RagBotPdfDto> entity);
}
