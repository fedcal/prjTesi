package com.msinfermiere.mapper.relation.medicinalesottoministrazione;

import com.msinfermiere.dto.infermiere.relation.MedicinaleSottoministrazioneDto;
import com.msinfermiere.entity.relation.MedicinaleSottoministrazione;
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
