package com.mspazienti.service.impl;

import com.mspazienti.dto.paziente.PazienteDto;
import com.mspazienti.esito.EsitoMessaggiRequestContextHolder;
import com.mspazienti.mapper.paziente.PazienteDtoMapper;
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
        return PazienteDtoMapper.INSTANCE.toDto(pazienteRepository.findAll());
    }

    @Override
    public PazienteDto getInfoPaziente(Integer idPaziente) {
        return PazienteDtoMapper.INSTANCE.toDto(pazienteRepository.findById(idPaziente).get());
    }
}
