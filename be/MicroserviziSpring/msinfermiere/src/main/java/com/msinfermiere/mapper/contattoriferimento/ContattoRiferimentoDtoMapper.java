package com.msinfermiere.mapper.contattoriferimento;

import com.msinfermiere.dto.infermiere.ContattoRiferimentoDto;
import com.msinfermiere.entity.ContattoRiferimento;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContattoRiferimentoDtoMapper {
    ContattoRiferimentoDtoMapper INSTANCE = Mappers.getMapper(ContattoRiferimentoDtoMapper.class);

    ContattoRiferimentoDto toDto(ContattoRiferimento entity);
    List<ContattoRiferimentoDto> toDto(List<ContattoRiferimento> entity);
}
