package com.filemanagement.controller;

import com.filemanagement.esito.EsitoMessaggiRequestContextHolder;
import com.filemanagement.esito.GenericResponseDto;
import com.filemanagement.service.DocumentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Validated
@RequestMapping("/documenti")
public class DocumentiController {

    @Autowired
    private EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;

    @Autowired
    private DocumentiService fileService;

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public ResponseEntity<GenericResponseDto<String>> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("uploadDir") String uploadDir) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(fileService.uploadFile(file,uploadDir)));
    }
}
