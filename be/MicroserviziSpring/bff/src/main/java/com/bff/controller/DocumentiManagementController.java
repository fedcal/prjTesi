package com.bff.controller;

import com.bff.esito.EsitoMessaggiRequestContextHolder;
import com.bff.esito.GenericResponseDto;
import com.bff.service.DocumentiManagementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documenti-management")
@Validated
@Tag(name = "Gestione file Controller",
        description = "Gestione dei file")
@CrossOrigin
@AllArgsConstructor
public class DocumentiManagementController {
    @Autowired
    private EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;

    @Autowired
    private DocumentiManagementService documentiManagementService;

    @PostMapping(value = "/addestramento-massivo")
    public ResponseEntity<GenericResponseDto<String>> addestramentoMassivo(@RequestParam("nomeBot") String nomeBot) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(documentiManagementService.addestramentoMassivo(nomeBot)));
    }

    @PostMapping(value = "/addestramento-singolo", consumes = {"multipart/form-data"})
    public ResponseEntity<GenericResponseDto<String>> addestramentoSingolo(@RequestParam("file") MultipartFile file, @RequestParam("nomeBot") String nomeBot) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(documentiManagementService.addestramentoSingolo(file,nomeBot)));
    }
}
