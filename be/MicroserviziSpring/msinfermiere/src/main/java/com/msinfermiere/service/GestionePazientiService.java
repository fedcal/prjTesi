package com.msinfermiere.service;

import com.msinfermiere.dto.infermiere.CartellaClinicaDto;
import com.msinfermiere.dto.infermiere.ContattoRiferimentoDto;
import com.msinfermiere.dto.infermiere.PazienteDto;
import com.msinfermiere.dto.infermiere.relation.MalattiaCartellaDto;
import com.msinfermiere.dto.infermiere.relation.MedicinaleCartellaDto;
import com.msinfermiere.dto.infermiere.relation.VisitaMedicaCartellaDto;
import com.msinfermiere.dto.request.gestionepaziente.*;

import java.util.List;

public interface GestionePazientiService {
    List<PazienteDto> getAllpazienti();

    List<PazienteDto> filterPazienti(FilterPazienteDto filterInfermiereDto);

    CartellaClinicaDto addCartellaClinica(AddCartellaClinicaDto addCartellaClinicaDto);

    ContattoRiferimentoDto addContattoRiferimento(AddContattoRiferimentoDto addContattoRiferimentoDto);

    PazienteDto addPaziente(AddPazientereDto addPazientereDto);

    MalattiaCartellaDto collegaMalattiaPaziente(CollegaMalattiaCartellaDto collegaMalattiaCartellaDto);

    MedicinaleCartellaDto collegaMedicinaleCartellaClinica(CollegaMedicinaleCartellaClinica collegaMedicinaleCartellaClinica);

    VisitaMedicaCartellaDto collegaVisitaMedicaCartellaClinica(CollegaVisitaMedicaCartellaDto collegaVisitaMedicaCartellaDto);

    CartellaClinicaDto visualizzaCartellaClinica(Integer idCartellaClinica);
}
