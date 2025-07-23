package com.msmedico.mapper.contattoriferimento;

import com.msmedico.dto.medico.ContattoRiferimentoDto;
import com.msmedico.entity.ContattoRiferimento;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContattoRiferimentoEntityMapper {
    ContattoRiferimentoEntityMapper INSTANCE = Mappers.getMapper(ContattoRiferimentoEntityMapper.class);

    ContattoRiferimento toEntity(ContattoRiferimentoDto dto);
    List<ContattoRiferimento> toEntity(List<ContattoRiferimentoDto> dto);
}
