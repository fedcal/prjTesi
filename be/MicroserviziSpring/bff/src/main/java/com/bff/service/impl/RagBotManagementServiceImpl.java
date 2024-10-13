package com.bff.service.impl;

import com.bff.dto.RagBotPdfDto;
import com.bff.dto.params.ragbotpdf.RegistraBotParams;
import com.bff.dto.params.ragbotpdf.ModificaBotParams;
import com.bff.dto.params.ragbotpdf.TrovaBotParams;
import com.bff.esito.EsitoMessaggiRequestContextHolder;
import com.bff.esito.GenericResponseConverter;
import com.bff.esito.GenericResponseDto;
import com.bff.filemanagement.api.RagBotControllerApi;
import com.bff.service.RagBotManagementService;
import com.bff.utils.ConverterObjectParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RagBotManagementServiceImpl implements RagBotManagementService {

    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;

    private final GenericResponseConverter genericResponseConverter;

    private final RagBotControllerApi ragBotControllerApi;


    @Override
    public List<RagBotPdfDto> elencoBot() {
        GenericResponseDto<List<RagBotPdfDto>> elencoBotResponse = genericResponseConverter.convertGenericResponseList(
                ragBotControllerApi.elencoBot(), RagBotPdfDto.class);
        return elencoBotResponse.getPayload();
    }

    @Override
    public RagBotPdfDto registraBot(RegistraBotParams registraBotParams) {
        GenericResponseDto<RagBotPdfDto> registraBotResponse = genericResponseConverter.convertGenericResponse(
                ragBotControllerApi.registraBot(ConverterObjectParameter.convertRegistraBot(registraBotParams)), RagBotPdfDto.class);
        return registraBotResponse.getPayload();
    }

    @Override
    public RagBotPdfDto modificaBot(ModificaBotParams modificaBotParams) {
        GenericResponseDto<RagBotPdfDto> modificaBotResponse = genericResponseConverter.convertGenericResponse(
                ragBotControllerApi.modificaBot(ConverterObjectParameter.convertModificaBot(modificaBotParams)), RagBotPdfDto.class);
        return modificaBotResponse.getPayload();
    }

    @Override
    public String eliminaBot(String nomeBot) {
        GenericResponseDto<String> botResponse = genericResponseConverter.convertGenericResponse(
                ragBotControllerApi.eliminaBot(nomeBot), String.class);
        return botResponse.getPayload();
    }

    @Override
    public RagBotPdfDto trovaBot(TrovaBotParams trovaBotParam) {
        GenericResponseDto<RagBotPdfDto> botResponse = genericResponseConverter.convertGenericResponse(
                ragBotControllerApi.trovaBot(ConverterObjectParameter.convertTrovaBot(trovaBotParam)), RagBotPdfDto.class);
        return botResponse.getPayload();
    }
}
