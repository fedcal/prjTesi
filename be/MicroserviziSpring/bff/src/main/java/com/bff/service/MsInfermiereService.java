package com.bff.service;

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

import java.util.List;

public interface MsInfermiereService {
    ResponseNormalMessageDto normalChat(String messagge);

    ResponseMessagePdfDto chatAddestrata(String messagge);

    ResponseEvalueteNormalChatDto evalueteNormalChat(String messagge);

    List<InfermiereDto> getAllInfermieri();

    List<InfermiereDto> filterInfermieri(FilterInfermiereDto filterInfermiereDto);

    InfermiereDto addInfermiere(AddInfermiereDto addInfermiereDto);

    InfermiereDto modificaInfermiere(ModificaInfermiereDto modificaInfermiereDto);

    Void deleteInfermiere(Integer idInfermiere);

    List<PazienteDto> getAllpazienti();

    List<PazienteDto> filterPazienti(FilterPazienteDto filterPazienteDto);

    CartellaClinicaDto addCartellaClinica(AddCartellaClinicaDto addCartellaClinicaDto);

    ContattoRiferimentoDto addContattoRiferimento(AddContattoRiferimentoDto addContattoRiferimentoDto);

    PazienteDto addPaziente(AddPazientereDto addPazientereDto);

    MalattiaCartellaDto collegaMalattiaPaziente(CollegaMalattiaCartellaDto collegaMalattiaCartellaDto);

    MedicinaleCartellaDto collegaMedicinaleCartellaClinica(CollegaMedicinaleCartellaClinica collegaMedicinaleCartellaClinica);

    VisitaMedicaCartellaDto collegaVisitaMedicaCartellaClinica(CollegaVisitaMedicaCartellaDto collegaVisitaMedicaCartellaDto);

    CartellaClinicaDto visualizzaCartellaClinica(Integer idCartellaClinica);
}
