package com.mspazienti.service.impl;

import com.mspazienti.dto.paziente.PazienteDto;
import com.mspazienti.esito.EsitoMessaggiRequestContextHolder;
import com.mspazienti.repository.PazienteRepository;
import com.mspazienti.service.PazienteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PazienteServiceImpl implements PazienteService {
    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;
    private final PazienteRepository pazienteRepository;

    @Override
    public List<PazienteDto> getListPazienti() {
        return List.of();
    }
}
