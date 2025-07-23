package com.bff.service.impl;

import com.bff.dto.request.msinfermiere.*;
import com.bff.dto.response.bot.ResponseEvalueteNormalChatDto;
import com.bff.dto.response.bot.ResponseMessagePdfDto;
import com.bff.dto.response.bot.ResponseNormalMessageDto;
import com.bff.dto.response.mssanitario.CartellaClinicaDto;
import com.bff.dto.response.mssanitario.ContattoRiferimentoDto;
import com.bff.dto.response.mssanitario.InfermiereDto;
import com.bff.dto.response.mssanitario.PazienteDto;
import com.bff.dto.response.mssanitario.relation.MalattiaCartellaDto;
import com.bff.dto.response.mssanitario.relation.MedicinaleCartellaDto;
import com.bff.dto.response.mssanitario.relation.VisitaMedicaCartellaDto;
import com.bff.esito.GenericResponseConverter;
import com.bff.esito.GenericResponseDto;
import com.bff.mapper.MsInfermiereParamsMapper;
import com.bff.msinfermiere.api.MsInfermiereChatBotSanitarioControllerApi;
import com.bff.msinfermiere.api.MsInfermiereGestioneInfermiereControllerApi;
import com.bff.msinfermiere.api.MsInfermiereGestionePazientiControllerApi;
import com.bff.service.MsInfermiereService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MsInfermiereServiceImpl implements MsInfermiereService {
    private final GenericResponseConverter genericResponseConverter;
    private final MsInfermiereChatBotSanitarioControllerApi msInfermiereChatBotSanitarioControllerApi;
    private final MsInfermiereGestioneInfermiereControllerApi msInfermiereGestioneInfermiereControllerApi;
    private final MsInfermiereGestionePazientiControllerApi msInfermiereGestionePazientiControllerApi;

    @Override
    public ResponseNormalMessageDto normalChat(String messagge) {
        GenericResponseDto<ResponseNormalMessageDto> normalChatResponse = genericResponseConverter.convertGenericResponse(msInfermiereChatBotSanitarioControllerApi.normalChat(messagge), ResponseNormalMessageDto.class);
        return normalChatResponse.getPayload();
    }

    @Override
    public ResponseMessagePdfDto chatAddestrata(String messagge) {
        GenericResponseDto<ResponseMessagePdfDto> chatAddestrata = genericResponseConverter.convertGenericResponse(msInfermiereChatBotSanitarioControllerApi.chatAddestrata(messagge), ResponseMessagePdfDto.class);
        return chatAddestrata.getPayload();
    }

    @Override
    public ResponseEvalueteNormalChatDto evalueteNormalChat(String messagge) {
        GenericResponseDto<ResponseEvalueteNormalChatDto> evaluationeChatAddestrata = genericResponseConverter.convertGenericResponse(msInfermiereChatBotSanitarioControllerApi.evalueteNormalChat(messagge), ResponseEvalueteNormalChatDto.class);
        return evaluationeChatAddestrata.getPayload();
    }

    @Override
    public List<InfermiereDto> getAllInfermieri() {
        GenericResponseDto<List<InfermiereDto>> evaluationeChatAddestrata = genericResponseConverter.convertGenericResponseList(msInfermiereGestioneInfermiereControllerApi.getAllInfermieri(), InfermiereDto.class);
        return evaluationeChatAddestrata.getPayload();
    }

    @Override
    public List<InfermiereDto> filterInfermieri(FilterInfermiereDto filterInfermiereDto) {
        GenericResponseDto<List<InfermiereDto>> filterInfermieriResponse = genericResponseConverter.convertGenericResponseList(msInfermiereGestioneInfermiereControllerApi.filterInfermieri(MsInfermiereParamsMapper.INSTANCE.mapFilterPrams(filterInfermiereDto)), InfermiereDto.class);
        return filterInfermieriResponse.getPayload();
    }

    @Override
    public InfermiereDto addInfermiere(AddInfermiereDto addInfermiereDto) {
        GenericResponseDto<InfermiereDto> addInfermiereResponse = genericResponseConverter.convertGenericResponse(msInfermiereGestioneInfermiereControllerApi.addInfermiere(MsInfermiereParamsMapper.INSTANCE.mapAddParams(addInfermiereDto)), InfermiereDto.class);
        return addInfermiereResponse.getPayload();
    }

    @Override
    public InfermiereDto modificaInfermiere(ModificaInfermiereDto modificaInfermiereDto) {
        GenericResponseDto<InfermiereDto> modificaInfermiereResponse = genericResponseConverter.convertGenericResponse(msInfermiereGestioneInfermiereControllerApi.modificaInfermiere(MsInfermiereParamsMapper.INSTANCE.mapModifyParams(modificaInfermiereDto)), InfermiereDto.class);
        return modificaInfermiereResponse.getPayload();
    }

    @Override
    public Void deleteInfermiere(Integer idInfermiere) {
        GenericResponseDto<Void> deleteInfermiereResponse = genericResponseConverter.convertGenericResponse(msInfermiereGestioneInfermiereControllerApi.deleteInfermiere(idInfermiere), Void.class);
        return deleteInfermiereResponse.getPayload();
    }

    @Override
    public List<PazienteDto> getAllpazienti() {
        GenericResponseDto<List<PazienteDto>> getAllpazientiResponse = genericResponseConverter.convertGenericResponseList(msInfermiereGestionePazientiControllerApi.getAllpazienti(), PazienteDto.class);
        return getAllpazientiResponse.getPayload();
    }

    @Override
    public List<PazienteDto> filterPazienti(FilterPazienteDto filterPazienteDto) {
        GenericResponseDto<List<PazienteDto>> filterPazientiRespone = genericResponseConverter.convertGenericResponseList(msInfermiereGestionePazientiControllerApi.filterPazienti(MsInfermiereParamsMapper.INSTANCE.mapFilterPazientiParams(filterPazienteDto)), PazienteDto.class);
        return filterPazientiRespone.getPayload();
    }

    @Override
    public CartellaClinicaDto addCartellaClinica(AddCartellaClinicaDto addCartellaClinicaDto) {
        GenericResponseDto<CartellaClinicaDto> addCartellaClinicaResponse = genericResponseConverter.convertGenericResponse(msInfermiereGestionePazientiControllerApi.addCartellaClinica(MsInfermiereParamsMapper.INSTANCE.mapAddCartellaClinicaParams(addCartellaClinicaDto)), CartellaClinicaDto.class);
        return addCartellaClinicaResponse.getPayload();
    }

    @Override
    public ContattoRiferimentoDto addContattoRiferimento(AddContattoRiferimentoDto addContattoRiferimentoDto) {
        GenericResponseDto<ContattoRiferimentoDto> addContattoRiferimentoResponse = genericResponseConverter.convertGenericResponse(msInfermiereGestionePazientiControllerApi.addContattoRiferimento(MsInfermiereParamsMapper.INSTANCE.mapAddContattoRiferimentoParams(addContattoRiferimentoDto)), ContattoRiferimentoDto.class);
        return addContattoRiferimentoResponse.getPayload();
    }

    @Override
    public PazienteDto addPaziente(AddPazientereDto addPazientereDto) {
        GenericResponseDto<PazienteDto> addPazienteResponse = genericResponseConverter.convertGenericResponse(msInfermiereGestionePazientiControllerApi.addPaziente(MsInfermiereParamsMapper.INSTANCE.mapAddPazientereParams(addPazientereDto)), PazienteDto.class);
        return addPazienteResponse.getPayload();
    }

    @Override
    public MalattiaCartellaDto collegaMalattiaPaziente(CollegaMalattiaCartellaDto collegaMalattiaCartellaDto) {
        GenericResponseDto<MalattiaCartellaDto> collegaMalattiaPazienteResponse = genericResponseConverter.convertGenericResponse(msInfermiereGestionePazientiControllerApi.collegaMalattiaPaziente(MsInfermiereParamsMapper.INSTANCE.mapCollegaMalattiaCartellaParams(collegaMalattiaCartellaDto)), MalattiaCartellaDto.class);
        return collegaMalattiaPazienteResponse.getPayload();
    }

    @Override
    public MedicinaleCartellaDto collegaMedicinaleCartellaClinica(CollegaMedicinaleCartellaClinica collegaMedicinaleCartellaClinica) {
        GenericResponseDto<MedicinaleCartellaDto> collegaMedicinaleCartellaClinicaResponse = genericResponseConverter.convertGenericResponse(msInfermiereGestionePazientiControllerApi.collegaMedicinaleCartellaClinica(MsInfermiereParamsMapper.INSTANCE.mapCollegaMedicinaleCartellaClinicaParams(collegaMedicinaleCartellaClinica)), MedicinaleCartellaDto.class);
        return collegaMedicinaleCartellaClinicaResponse.getPayload();
    }

    @Override
    public VisitaMedicaCartellaDto collegaVisitaMedicaCartellaClinica(CollegaVisitaMedicaCartellaDto collegaVisitaMedicaCartellaDto) {
        GenericResponseDto<VisitaMedicaCartellaDto> collegaVisitaMedicaCartellaClinicaResponse = genericResponseConverter.convertGenericResponse(msInfermiereGestionePazientiControllerApi.collegaVisitaMedicaCartellaClinica(MsInfermiereParamsMapper.INSTANCE.mapCollegaVisitaMedicaCartellaParams(collegaVisitaMedicaCartellaDto)), VisitaMedicaCartellaDto.class);
        return collegaVisitaMedicaCartellaClinicaResponse.getPayload();
    }

    @Override
    public CartellaClinicaDto visualizzaCartellaClinica(Integer idCartellaClinica) {
        GenericResponseDto<CartellaClinicaDto> visualizzaCartellaClinicaResponse = genericResponseConverter.convertGenericResponse(msInfermiereGestionePazientiControllerApi.visualizzaCartellaClinica(idCartellaClinica), CartellaClinicaDto.class);
        return visualizzaCartellaClinicaResponse.getPayload();
    }
}
