package com.bff.controller;

import com.bff.dto.request.msinfermiere.*;
import com.bff.dto.response.bot.ResponseEvalueteNormalChatDto;
import com.bff.dto.response.bot.ResponseMessagePdfDto;
import com.bff.dto.response.bot.ResponseNormalMessageDto;
import com.bff.dto.response.mssanitario.CartellaClinicaDto;
import com.bff.dto.response.mssanitario.ContattoRiferimentoDto;
import com.bff.dto.response.mssanitario.InfermiereDto;
import com.bff.dto.response.mssanitario.PazienteDto;
import com.bff.dto.response.mssanitario.relation.MalattiaCartellaDto;
import com.bff.dto.response.mssanitario.relation.MedicinaleCartellaDto;
import com.bff.dto.response.mssanitario.relation.VisitaMedicaCartellaDto;
import com.bff.esito.EsitoMessaggiRequestContextHolder;
import com.bff.esito.GenericResponseDto;
import com.bff.service.MsInfermiereService;
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

import java.util.List;

@RestController
@RequestMapping("/ms-inferimiere")
@Validated
@Tag(name = "Ms Infermieri Controller",
        description = "Ms infermieri")
@AllArgsConstructor
public class MsInfermiereController {

    @Autowired
    private EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;

    @Autowired
    private MsInfermiereService msInfermiereService;

    @Operation(summary = "Chat normale",
            description = "Invio di un messaggio al bot AI sfruttando l'LLM non addestrato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/normal-chat", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<ResponseNormalMessageDto>> normalChat(@RequestParam String messagge) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(msInfermiereService.normalChat(messagge)));
    }

    @Operation(summary = "Chat addestrata",
            description = "Invio di un messaggio al bot AI sfruttando l'LLM addestrato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/chat-addestrata", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<ResponseMessagePdfDto>> chatAddestrata(@RequestParam String messagge) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(msInfermiereService.chatAddestrata(messagge)));
    }

    @Operation(summary = "Chat Valutazione pdf",
            description = "Chat Valutazione pdf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/evaluete-normal-chat", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<ResponseEvalueteNormalChatDto>> evalueteNormalChat(@RequestParam String messagge) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(msInfermiereService.evalueteNormalChat(messagge)));
    }

    @Operation(summary = "All Infermieri",
            description = "Restituisce tutti gli infermieri")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/all-infermieri", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<List<InfermiereDto>>> getAllInfermieri() {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(msInfermiereService.getAllInfermieri()));
    }

    @Operation(summary = "Filter infermieri",
            description = "Restituisce gli infermieri filtrati")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/filter-infermiere", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<List<InfermiereDto>>> filterInfermieri(@RequestBody FilterInfermiereDto filterInfermiereDto) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(msInfermiereService.filterInfermieri(filterInfermiereDto)));
    }

    @Operation(summary = "Add Infermiere",
            description = "Aggiunge un infermiere")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/add-infermiere", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<InfermiereDto>> addInfermiere(@RequestBody AddInfermiereDto addInfermiereDto) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(msInfermiereService.addInfermiere(addInfermiereDto)));
    }


    @Operation(summary = "Modifica info infermiere",
            description = "Modifica le informazioni dell'infermiere")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PutMapping(value = "/modifica", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<InfermiereDto>> modificaInfermiere(@RequestBody ModificaInfermiereDto modificaInfermiereDto) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(msInfermiereService.modificaInfermiere(modificaInfermiereDto)));
    }

    @Operation(summary = "Delete Infermiere",
            description = "Elimina l'infermiere selezionato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<Void>> deleteInfermiere(@RequestParam("idInfermiere") Integer idInfermiere) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(msInfermiereService.deleteInfermiere(idInfermiere)));
    }

    @Operation(summary = "All Pazienti",
            description = "Restituisce tutti i pazienti")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<List<PazienteDto>>> getAllpazienti() {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(msInfermiereService.getAllpazienti()));
    }

    @Operation(summary = "Filter infermieri",
            description = "Restituisce gli infermieri filtrati")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @GetMapping(value = "/filter-pazienti", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<List<PazienteDto>>> filterPazienti(@RequestBody FilterPazienteDto filterPazienteDto) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(msInfermiereService.filterPazienti(filterPazienteDto)));
    }

    @Operation(summary = "Add Cartella Clinica",
            description = "Aggiunge una cartella clinica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/add-cartella-clinica", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<CartellaClinicaDto>> addCartellaClinica(@RequestBody AddCartellaClinicaDto addCartellaClinicaDto) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(msInfermiereService.addCartellaClinica(addCartellaClinicaDto)));
    }

    @Operation(summary = "Add Contatto riferimento",
            description = "Aggiunge un contatto riferimento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/add-contatto-riferimento", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<ContattoRiferimentoDto>> addContattoRiferimento(@RequestBody AddContattoRiferimentoDto addContattoRiferimentoDto) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(msInfermiereService.addContattoRiferimento(addContattoRiferimentoDto)));
    }

    @Operation(summary = "Add Paziente",
            description = "Aggiunge un paziente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/add-paziente", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<PazienteDto>> addPaziente(@RequestBody AddPazientereDto addPazientereDto) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(msInfermiereService.addPaziente(addPazientereDto)));
    }

    @Operation(summary = "Add Paziente",
            description = "Aggiunge un paziente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/collega-malattia-cartella-clinica", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<MalattiaCartellaDto>> collegaMalattiaPaziente(@RequestBody CollegaMalattiaCartellaDto collegaMalattiaCartellaDto) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(msInfermiereService.collegaMalattiaPaziente(collegaMalattiaCartellaDto)));
    }

    @Operation(summary = "Add Paziente",
            description = "Aggiunge un paziente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/collega-medicinale-cartella-clinica", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<MedicinaleCartellaDto>> collegaMedicinaleCartellaClinica(@RequestBody CollegaMedicinaleCartellaClinica collegaMedicinaleCartellaClinica) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(msInfermiereService.collegaMedicinaleCartellaClinica(collegaMedicinaleCartellaClinica)));
    }

    @Operation(summary = "Add Paziente",
            description = "Aggiunge un paziente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/collega-visita-medica-cartella-clinica", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<VisitaMedicaCartellaDto>> collegaVisitaMedicaCartellaClinica(@RequestBody CollegaVisitaMedicaCartellaDto collegaVisitaMedicaCartellaDto) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(msInfermiereService.collegaVisitaMedicaCartellaClinica(collegaVisitaMedicaCartellaDto)));
    }

    @Operation(summary = "Add Paziente",
            description = "Aggiunge un paziente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operazione andata a buon fine"),
            @ApiResponse(responseCode = "500", description = "Errore di sistema")
    })
    @PostMapping(value = "/visualizza-cartella-clinica/{idCartellaClinica}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseDto<CartellaClinicaDto>> visualizzaCartellaClinica(@RequestParam("idCartellaClinica") Integer idCartellaClinica) {
        return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse(msInfermiereService.visualizzaCartellaClinica(idCartellaClinica)));
    }
}
