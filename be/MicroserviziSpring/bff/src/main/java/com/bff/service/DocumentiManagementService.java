package com.bff.service;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentiManagementService {
    String addestramentoMassivo(String nomeBot);

    String addestramentoSingolo(MultipartFile file, String nomeBot);
}
