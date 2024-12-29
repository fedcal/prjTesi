package com.mspazienti.mapper.relation.operazioneprescrizione;

import com.mspazienti.dto.paziente.relation.OperazionePrescrizioneDto;
import com.mspazienti.entity.relation.OperazionePrescrizione;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OperazionePrescrizioneDtoMapper {
    OperazionePrescrizioneDtoMapper INSTANCE = Mappers.getMapper(OperazionePrescrizioneDtoMapper.class);

    OperazionePrescrizioneDto toDto(OperazionePrescrizione operazione);
    List<OperazionePrescrizioneDto> toDto(List<OperazionePrescrizione> operazione);
}
