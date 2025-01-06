package com.msinfermiere.mapper.contattoriferimento;

import com.msinfermiere.dto.infermiere.ContattoRiferimentoDto;
import com.msinfermiere.dto.request.gestionepaziente.AddContattoRiferimentoDto;
import com.msinfermiere.entity.ContattoRiferimento;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContattoRiferimentoEntityMapper {
    ContattoRiferimentoEntityMapper INSTANCE = Mappers.getMapper(ContattoRiferimentoEntityMapper.class);

    ContattoRiferimento toEntity(ContattoRiferimentoDto dto);
    List<ContattoRiferimento> toEntity(List<ContattoRiferimentoDto> dto);

    ContattoRiferimento toEntity(AddContattoRiferimentoDto addContattoRiferimentoDto);
}
