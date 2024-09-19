package com.bff.service.impl;

import com.bff.esito.EsitoMessaggiRequestContextHolder;
import com.bff.esito.GenericResponseConverter;
import com.bff.esito.GenericResponseDto;
import com.bff.filemanagement.api.DocumentiControllerApi;
import com.bff.service.DocumentiManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DocumentiManagementServiceImpl implements DocumentiManagementService {

    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;

    private final GenericResponseConverter genericResponseConverter;

    private final DocumentiControllerApi documentiControllerApi;

    @Override
    public String addestramentoMassivo(String nomeBot) {
        GenericResponseDto<String> addestramentoMassivoResponse = genericResponseConverter.convertGenericResponse(
                documentiControllerApi.addestramentoMassivo(nomeBot), String.class);
        return addestramentoMassivoResponse.getPayload();
    }

    @Override
    public String addestramentoSingolo(MultipartFile file, String nomeBot) {


        File fileFromMultipart = null;
        try {
            fileFromMultipart = File.createTempFile("temp", file.getOriginalFilename());
            file.transferTo(fileFromMultipart);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        GenericResponseDto<String> addestramentoSingoloResponse = genericResponseConverter.convertGenericResponse(
                documentiControllerApi.addestramentoSingolo(nomeBot, fileFromMultipart), String.class);

        return addestramentoSingoloResponse.getPayload();
    }
}
