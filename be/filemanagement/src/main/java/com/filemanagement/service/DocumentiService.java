package com.filemanagement.service;

import com.filemanagement.dto.params.documenti.ModificaDocumentiParams;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentiService {
    public String uploadFile(MultipartFile file, String uploadDir);

    public String deleteFile(String nameFile, String uploadDir);

    String rinominaFile(ModificaDocumentiParams modificaDocumenti);
}
