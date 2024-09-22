package com.systemmanagement.controller;

import com.systemmanagement.dto.DocumentiDto;
import com.systemmanagement.dto.params.documenti.EliminaDocumento;
import com.systemmanagement.dto.params.documenti.FindDocumentoParams;
import com.systemmanagement.dto.params.documenti.ModificaDocumentiParams;
import com.systemmanagement.esito.EsitoMessaggiRequestContextHolder;
import com.systemmanagement.esito.GenericResponseDto;
import com.systemmanagement.service.DocumentiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Validated
@RequestMapping("/documenti")
@Tag(name = "Documenti Controller",
        description = "Gestione dei documenti")
@AllArgsConstructor
public class DocumentiController {

    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;
    private final DocumentiService fileService;

    /*@PostMapping(value = "/upload", consumes = {"multipart/form-data"})
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
    public ResponseEntity<GenericResponseDto<String>> eliminaFile(EliminaDocumento eliminaDocumento) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(fileService.eliminaFile(eliminaDocumento)));
    }

    @GetMapping(value = "/elenco", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<List<DocumentiDto>>> elencoFile() {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(fileService.elencoFile()));
    }

    @GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<DocumentiDto>> findFile(FindDocumentoParams findDocumentoParams) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(fileService.findFile(findDocumentoParams)));
    }*/

    @Operation(summary = "Addestamento massivo bot",
            description = "Specificando il nome del bot, si avvia l'addestramento massivo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/addestramento-massivo")
    public ResponseEntity<GenericResponseDto<String>> addestramentoMassivo(@RequestParam("nomeBot") String nomeBot) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(fileService.addestramentoMassivo(nomeBot)));
    }

    @Operation(summary = "Addestamento bot singolo file",
            description = "Specificando il nome del bot e caricando un file, si avvia l'addestramento per il singolo file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/addestramento-singolo", consumes = {"multipart/form-data"})
    public ResponseEntity<GenericResponseDto<String>> addestramentoSingolo(@RequestParam("file") MultipartFile file, @RequestParam("nomeBot") String nomeBot) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(fileService.addestramentoSingolo(file,nomeBot)));
    }

}
