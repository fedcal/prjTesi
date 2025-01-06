package com.msinfermiere.controller;

import com.msinfermiere.dto.infermiere.InfermiereDto;
import com.msinfermiere.dto.request.gestioneinfermiere.AddInfermiereDto;
import com.msinfermiere.dto.request.gestioneinfermiere.FilterInfermiereDto;
import com.msinfermiere.dto.request.gestioneinfermiere.ModificaInfermiereDto;
import com.msinfermiere.esito.EsitoMessaggiRequestContextHolder;
import com.msinfermiere.esito.GenericResponseDto;
import com.msinfermiere.service.GestioneInfermiriService;
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

import static com.msinfermiere.constants.WebConstants.MS_INFERMIERI;

@RestController
@RequestMapping(MS_INFERMIERI + "/gestione-infermieri")
@Validated
@Tag(name = "MsInfermiereGestioneInfermiereController",
        description = "Gestione degli infermieri")
@CrossOrigin
@AllArgsConstructor
public class GestioneInfermiereController {

    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;
    private final GestioneInfermiriService gestioneInfermiriService;

    @Operation(summary = "All Infermieri",
            description = "Restituisce tutti gli infermieri")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<List<InfermiereDto>>> getAllInfermieri() {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(gestioneInfermiriService.getAllInfermieri()));
    }

    @Operation(summary = "Filter infermieri",
            description = "Restituisce gli infermieri filtrati")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/filter-infermiere", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<List<InfermiereDto>>> filterInfermieri(@RequestBody FilterInfermiereDto filterInfermiereDto) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(gestioneInfermiriService.filterInfermieri(filterInfermiereDto)));
    }

    @Operation(summary = "Add Infermiere",
            description = "Aggiunge un infermiere")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/add-infermiere", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<InfermiereDto>> addInfermiere(@RequestBody AddInfermiereDto addInfermiereDto) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(gestioneInfermiriService.addInfermiere(addInfermiereDto)));
    }


    @Operation(summary = "Modifica info infermiere",
            description = "Modifica le informazioni dell'infermiere")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PutMapping(value = "/modifica", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<InfermiereDto>> modificaInfermiere(@RequestBody ModificaInfermiereDto modificaInfermiereDto) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(gestioneInfermiriService.modificaInfermiere(modificaInfermiereDto)));
    }

    @Operation(summary = "Delete Infermiere",
            description = "Elimina l'infermiere selezionato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<Void>> deleteInfermiere(@RequestParam("idInfermiere") Integer idInfermiere) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(gestioneInfermiriService.deleteInfermiere(idInfermiere)));
    }
}
