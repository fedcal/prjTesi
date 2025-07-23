package com.bff.service;

import com.bff.dto.PdfAddestratiDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentiManagementService {
    List<PdfAddestratiDto> addestramentoMassivo(String nomeBot);

    String addestramentoSingolo(MultipartFile file, String nomeBot);
}
