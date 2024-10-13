package com.bff.service.impl;

import com.bff.dto.PdfAddestratiDto;
import com.bff.esito.EsitoMessaggiRequestContextHolder;
import com.bff.esito.GenericResponseConverter;
import com.bff.esito.GenericResponseDto;
import com.bff.filemanagement.api.DocumentiControllerApi;
import com.bff.service.DocumentiManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentiManagementServiceImpl implements DocumentiManagementService {

    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;

    private final GenericResponseConverter genericResponseConverter;

    private final DocumentiControllerApi documentiControllerApi;

    @Override
    public List<PdfAddestratiDto> addestramentoMassivo(String nomeBot) {
        GenericResponseDto<List<PdfAddestratiDto>> addestramentoMassivoResponse = genericResponseConverter.convertGenericResponseList(
                documentiControllerApi.addestramentoMassivo(nomeBot), PdfAddestratiDto.class);
        return addestramentoMassivoResponse.getPayload();
    }

    @Override
    public String addestramentoSingolo(MultipartFile file, String nomeBot) {


        File fileFromMultipart = null;
        try {
            fileFromMultipart = new File(System.getProperty("java.io.tmpdir"), file.getOriginalFilename());
            if (fileFromMultipart.exists()) {
                fileFromMultipart.delete();
            }
            file.transferTo(fileFromMultipart);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        GenericResponseDto<String> addestramentoSingoloResponse = genericResponseConverter.convertGenericResponse(
                documentiControllerApi.addestramentoSingolo(nomeBot, fileFromMultipart), String.class);

        return addestramentoSingoloResponse.getPayload();
    }
}
