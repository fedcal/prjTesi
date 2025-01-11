package com.msinfermiere.controller;

import com.msinfermiere.dto.infermiere.CartellaClinicaDto;
import com.msinfermiere.dto.infermiere.ContattoRiferimentoDto;
import com.msinfermiere.dto.infermiere.PazienteDto;
import com.msinfermiere.dto.infermiere.relation.MalattiaCartellaDto;
import com.msinfermiere.dto.infermiere.relation.MedicinaleCartellaDto;
import com.msinfermiere.dto.infermiere.relation.VisitaMedicaCartellaDto;
import com.msinfermiere.dto.request.gestionepaziente.*;
import com.msinfermiere.esito.EsitoMessaggiRequestContextHolder;
import com.msinfermiere.esito.GenericResponseDto;
import com.msinfermiere.service.GestionePazientiService;
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
@RequestMapping(MS_INFERMIERI + "/gestione-pazienti")
@Validated
@Tag(name = "MsInfermiereGestionePazientiController",
        description = "Gestione dei pazienti da parte degli infermieri")
@CrossOrigin
@AllArgsConstructor
public class GestionePazienteInfermiereController {
    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;
    private final GestionePazientiService gestionePazientiService;

    @Operation(summary = "All Pazienti",
            description = "Restituisce tutti i pazienti")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<List<PazienteDto>>> getAllpazienti() {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(gestionePazientiService.getAllpazienti()));
    }

    @Operation(summary = "Filter infermieri",
            description = "Restituisce gli infermieri filtrati")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/filter-pazienti", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<List<PazienteDto>>> filterPazienti(@RequestBody FilterPazienteDto filterPazienteDto) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(gestionePazientiService.filterPazienti(filterPazienteDto)));
    }

    @Operation(summary = "Add Cartella Clinica",
            description = "Aggiunge una cartella clinica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/add-cartella-clinica", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<CartellaClinicaDto>> addCartellaClinica(@RequestBody AddCartellaClinicaDto addCartellaClinicaDto) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(gestionePazientiService.addCartellaClinica(addCartellaClinicaDto)));
    }

    @Operation(summary = "Add Contatto riferimento",
            description = "Aggiunge un contatto riferimento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/add-contatto-riferimento", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<ContattoRiferimentoDto>> addContattoRiferimento(@RequestBody AddContattoRiferimentoDto addContattoRiferimentoDto) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(gestionePazientiService.addContattoRiferimento(addContattoRiferimentoDto)));
    }

    @Operation(summary = "Add Paziente",
            description = "Aggiunge un paziente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/add-paziente", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<PazienteDto>> addPaziente(@RequestBody AddPazientereDto addPazientereDto) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(gestionePazientiService.addPaziente(addPazientereDto)));
    }

    @Operation(summary = "Add Paziente",
            description = "Aggiunge un paziente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/collega-malattia-cartella-clinica", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<MalattiaCartellaDto>> collegaMalattiaPaziente(@RequestBody CollegaMalattiaCartellaDto collegaMalattiaCartellaDto) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(gestionePazientiService.collegaMalattiaPaziente(collegaMalattiaCartellaDto)));
    }

    @Operation(summary = "Add Paziente",
            description = "Aggiunge un paziente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/collega-medicinale-cartella-clinica", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<MedicinaleCartellaDto>> collegaMedicinaleCartellaClinica(@RequestBody CollegaMedicinaleCartellaClinica collegaMedicinaleCartellaClinica) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(gestionePazientiService.collegaMedicinaleCartellaClinica(collegaMedicinaleCartellaClinica)));
    }

    @Operation(summary = "Add Paziente",
            description = "Aggiunge un paziente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/collega-visita-medica-cartella-clinica", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<VisitaMedicaCartellaDto>> collegaVisitaMedicaCartellaClinica(@RequestBody CollegaVisitaMedicaCartellaDto collegaVisitaMedicaCartellaDto) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(gestionePazientiService.collegaVisitaMedicaCartellaClinica(collegaVisitaMedicaCartellaDto)));
    }

    @Operation(summary = "Add Paziente",
            description = "Aggiunge un paziente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/visualizza-cartella-clinica/{idCartellaClinica}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<CartellaClinicaDto>> visualizzaCartellaClinica(@RequestParam("idCartellaClinica") Integer idCartellaClinica) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(gestionePazientiService.visualizzaCartellaClinica(idCartellaClinica)));
    }
}
