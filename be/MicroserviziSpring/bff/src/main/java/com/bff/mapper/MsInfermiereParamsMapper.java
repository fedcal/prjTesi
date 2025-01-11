package com.bff.mapper;

import com.bff.msinfermiere.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MsInfermiereParamsMapper {
    MsInfermiereParamsMapper INSTANCE = Mappers.getMapper(MsInfermiereParamsMapper.class);

    FilterInfermiereDto mapFilterPrams(com.bff.dto.request.msinfermiere.FilterInfermiereDto filterInfermiereDto);

    AddInfermiereDto mapAddParams(com.bff.dto.request.msinfermiere.AddInfermiereDto addInfermiereDto);

    ModificaInfermiereDto mapModifyParams(com.bff.dto.request.msinfermiere.ModificaInfermiereDto modificaInfermiereDto);

    FilterPazienteDto mapFilterPazientiParams(com.bff.dto.request.msinfermiere.FilterPazienteDto filterPazienteDto);

    AddCartellaClinicaDto mapAddCartellaClinicaParams(com.bff.dto.request.msinfermiere.AddCartellaClinicaDto addCartellaClinicaDto);

    AddContattoRiferimentoDto mapAddContattoRiferimentoParams(com.bff.dto.request.msinfermiere.AddContattoRiferimentoDto addContattoRiferimentoDto);

    AddPazientereDto mapAddPazientereParams(com.bff.dto.request.msinfermiere.AddPazientereDto addPazientereDto);

    CollegaMalattiaCartellaDto mapCollegaMalattiaCartellaParams(com.bff.dto.request.msinfermiere.CollegaMalattiaCartellaDto collegaMalattiaCartellaDto);

    CollegaMedicinaleCartellaClinica mapCollegaMedicinaleCartellaClinicaParams(com.bff.dto.request.msinfermiere.CollegaMedicinaleCartellaClinica collegaMedicinaleCartellaClinica);

    CollegaVisitaMedicaCartellaDto mapCollegaVisitaMedicaCartellaParams(com.bff.dto.request.msinfermiere.CollegaVisitaMedicaCartellaDto collegaVisitaMedicaCartellaDto);
}
