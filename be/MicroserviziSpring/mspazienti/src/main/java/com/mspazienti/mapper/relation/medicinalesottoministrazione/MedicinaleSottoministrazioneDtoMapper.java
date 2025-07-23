package com.mspazienti.mapper.relation.medicinalesottoministrazione;

import com.mspazienti.dto.paziente.relation.MedicinaleSottoministrazioneDto;
import com.mspazienti.entity.relation.MedicinaleSottoministrazione;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicinaleSottoministrazioneDtoMapper {
    MedicinaleSottoministrazioneDtoMapper INSTANCE = Mappers.getMapper(MedicinaleSottoministrazioneDtoMapper.class);

    MedicinaleSottoministrazioneDto toDto(MedicinaleSottoministrazione medicinaleSottoministrazione);
    List<MedicinaleSottoministrazioneDto> toDto(List<MedicinaleSottoministrazione> medicinaleSottoministrazione);
}
