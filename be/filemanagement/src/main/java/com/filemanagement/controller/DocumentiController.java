package com.filemanagement.controller;

import com.filemanagement.dto.params.documenti.EliminaDocumento;
import com.filemanagement.dto.params.documenti.ModificaDocumentiParams;
import com.filemanagement.esito.EsitoMessaggiRequestContextHolder;
import com.filemanagement.esito.GenericResponseDto;
import com.filemanagement.service.DocumentiService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Validated
@RequestMapping("/documenti")
@Tag(name = "Documenti Controller",
        description = "Gestione dei documenti")
public class DocumentiController {

    @Autowired
    private EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;

    @Autowired
    private DocumentiService fileService;

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public ResponseEntity<GenericResponseDto<String>> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("uploadDir") String uploadDir) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(fileService.uploadFile(file,uploadDir)));
    }

    @PostMapping(value = "/rinomina", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<String>> rinominaFile(@RequestBody ModificaDocumentiParams modificaDocumenti) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(fileService.rinominaFile(modificaDocumenti)));
    }

    @PostMapping(value = "/sposta", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<String>> spostaFile(@RequestBody ModificaDocumentiParams modificaDocumenti) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(fileService.spostaFile(modificaDocumenti)));
    }

    @DeleteMapping(value = "/elimina", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<String>> eliminaFile(@RequestBody EliminaDocumento eliminaDocumento) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(fileService.eliminaFile(eliminaDocumento)));
    }
}
