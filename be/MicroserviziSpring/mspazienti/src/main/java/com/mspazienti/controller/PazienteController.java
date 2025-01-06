package com.mspazienti.controller;

import com.mspazienti.dto.paziente.PazienteDto;
import com.mspazienti.esito.EsitoMessaggiRequestContextHolder;
import com.mspazienti.esito.GenericResponseDto;
import com.mspazienti.service.PazienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mspazienti.constants.WebConstants.REST_CONTEX_STRING;

@RestController
@RequestMapping(REST_CONTEX_STRING + "/paziente")
@Validated
@Tag(name = "MsPazienteController",
        description = "Gestione della chat")
@CrossOrigin
@AllArgsConstructor
public class PazienteController {
    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;
    private final PazienteService pazienteService;

    @Operation(summary = "Lista di pazienti",
            description = "Lista di tutti i pazienti")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<List<PazienteDto>>> getListPazienti() {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(pazienteService.getListPazienti()));
    }

    @Operation(summary = "Paziente",
            description = "Ottenimento informazione paziente tramite id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/info/{idPaziente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<PazienteDto>> getInfoPaziente(@RequestParam("idPaziente") Integer idPaziente) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(pazienteService.getInfoPaziente(idPaziente)));
    }
}
