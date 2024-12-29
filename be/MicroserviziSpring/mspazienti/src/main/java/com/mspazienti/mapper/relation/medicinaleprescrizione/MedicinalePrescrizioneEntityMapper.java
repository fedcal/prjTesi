package com.mspazienti.mapper.relation.medicinaleprescrizione;

import com.mspazienti.dto.paziente.relation.MedicinalePrescrizioneDto;
import com.mspazienti.entity.relation.MedicinalePrescrizione;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicinalePrescrizioneEntityMapper {
    MedicinalePrescrizioneEntityMapper INSTANCE = Mappers.getMapper(MedicinalePrescrizioneEntityMapper.class);

    MedicinalePrescrizione toEntity(MedicinalePrescrizioneDto dto);
    List<MedicinalePrescrizione> toEntity(List<MedicinalePrescrizioneDto> dto);
}
