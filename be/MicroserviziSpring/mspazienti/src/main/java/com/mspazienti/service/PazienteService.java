package com.mspazienti.service;

import com.mspazienti.dto.paziente.PazienteDto;

import java.util.List;

public interface PazienteService {
    List<PazienteDto> getListPazienti();

    PazienteDto getInfoPaziente(Integer idPaziente);
}
