package com.msinfermiere.mapper.relation.medicinalesottoministrazione;

import com.msinfermiere.dto.infermiere.relation.MedicinaleSottoministrazioneDto;
import com.msinfermiere.entity.relation.MedicinaleSottoministrazione;
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
