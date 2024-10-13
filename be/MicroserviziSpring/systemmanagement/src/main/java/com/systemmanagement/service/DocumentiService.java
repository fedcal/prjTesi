package com.systemmanagement.service;

import com.systemmanagement.dto.DocumentiDto;
import com.systemmanagement.dto.PdfAddestratiDto;
import com.systemmanagement.dto.params.documenti.EliminaDocumento;
import com.systemmanagement.dto.params.documenti.FindDocumentoParams;
import com.systemmanagement.dto.params.documenti.ModificaDocumentiParams;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentiService {
    String uploadFile(MultipartFile file, String uploadDir);

    String rinominaFile(ModificaDocumentiParams modificaDocumenti);

    String spostaFile(ModificaDocumentiParams modificaDocumenti);

    String eliminaFile(EliminaDocumento eliminaDocumento);

    List<DocumentiDto> elencoFile();

    DocumentiDto findFile(FindDocumentoParams findDocumentoParams);

    String addestramentoSingolo(MultipartFile file, String nomeBot);

    List<PdfAddestratiDto> addestramentoMassivo(String nomeBot);
}
