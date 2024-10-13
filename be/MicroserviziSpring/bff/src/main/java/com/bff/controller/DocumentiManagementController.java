package com.bff.controller;

import com.bff.dto.PdfAddestratiDto;
import com.bff.esito.EsitoMessaggiRequestContextHolder;
import com.bff.esito.GenericResponseDto;
import com.bff.service.DocumentiManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/documenti-management")
@Validated
@Tag(name = "Gestione file Controller",
        description = "Gestione dei file")
@AllArgsConstructor
public class DocumentiManagementController {
    @Autowired
    private EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;

    @Autowired
    private DocumentiManagementService documentiManagementService;

    @Operation(summary = "Addestamento massivo bot",
            description = "Specificando il nome del bot, si avvia l'addestramento massivo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/addestramento-massivo")
    public ResponseEntity<GenericResponseDto<List<PdfAddestratiDto>>> addestramentoMassivo(@RequestParam("nomeBot") String nomeBot) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(documentiManagementService.addestramentoMassivo(nomeBot)));
    }

    @Operation(summary = "Addestamento bot singolo file",
            description = "Specificando il nome del bot e caricando un file, si avvia l'addestramento per il singolo file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/addestramento-singolo", consumes = {"multipart/form-data"})
    public ResponseEntity<GenericResponseDto<String>> addestramentoSingolo(@RequestParam("file") MultipartFile file, @RequestParam("nomeBot") String nomeBot) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(documentiManagementService.addestramentoSingolo(file,nomeBot)));
    }
}
