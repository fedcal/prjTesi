package com.filemanagement.controller;

import com.filemanagement.dto.params.CartellaParams;
import com.filemanagement.dto.params.ModificaCartellaParams;
import com.filemanagement.esito.EsitoMessaggiRequestContextHolder;
import com.filemanagement.esito.GenericResponseDto;
import com.filemanagement.service.CartelleService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartelle")
public class CartelleController {

    @Autowired
    private EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;

    @Autowired
    private CartelleService cartelleService;

    @PutMapping(value = "/crea", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<GenericResponseDto<String>> aggiungiCartella(@Valid @ParameterObject CartellaParams cartellaParams) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(cartelleService.aggiungiCartella(cartellaParams)));
    }

    @PostMapping(value = "/rinomina", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<GenericResponseDto<String>> rinominaCartella(@Valid @ParameterObject ModificaCartellaParams cartellaParams) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(cartelleService.rinominaCartella(cartellaParams)));
    }

    @DeleteMapping(value = "/elimina", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<GenericResponseDto<String>> eliminaCartella(@Valid @ParameterObject CartellaParams cartellaParams) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(cartelleService.eliminaCartella(cartellaParams)));
    }
}
