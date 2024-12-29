package com.mspazienti.mapper.relation.medicinalesottoministrazione;

import com.mspazienti.dto.paziente.relation.MedicinaleSottoministrazioneDto;
import com.mspazienti.entity.relation.MedicinaleSottoministrazione;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicinaleSottoministrazioneEntityMapper {
    MedicinaleSottoministrazioneEntityMapper INSTANCE = Mappers.getMapper(MedicinaleSottoministrazioneEntityMapper.class);

    MedicinaleSottoministrazione toEntity(MedicinaleSottoministrazioneDto dto);
    List<MedicinaleSottoministrazione> toEntity(List<MedicinaleSottoministrazioneDto> dto);
}
