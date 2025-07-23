package com.msinfermiere.service;

import com.msinfermiere.dto.infermiere.InfermiereDto;
import com.msinfermiere.dto.request.gestioneinfermiere.AddInfermiereDto;
import com.msinfermiere.dto.request.gestioneinfermiere.FilterInfermiereDto;
import com.msinfermiere.dto.request.gestioneinfermiere.ModificaInfermiereDto;

import java.util.List;

public interface GestioneInfermiriService {
    List<InfermiereDto> getAllInfermieri();

    InfermiereDto addInfermiere(AddInfermiereDto addInfermiereDto);

    List<InfermiereDto> filterInfermieri(FilterInfermiereDto filterInfermiereDto);

    InfermiereDto modificaInfermiere(ModificaInfermiereDto modificaInfermiereDto);

    Void deleteInfermiere(Integer idInfermiere);
}
