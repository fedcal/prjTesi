package com.filemanagement.service;

import com.filemanagement.dto.params.documenti.EliminaDocumento;
import com.filemanagement.dto.params.documenti.ModificaDocumentiParams;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentiService {
    String uploadFile(MultipartFile file, String uploadDir);

    String rinominaFile(ModificaDocumentiParams modificaDocumenti);

    String spostaFile(ModificaDocumentiParams modificaDocumenti);

    String eliminaFile(EliminaDocumento eliminaDocumento);
}
