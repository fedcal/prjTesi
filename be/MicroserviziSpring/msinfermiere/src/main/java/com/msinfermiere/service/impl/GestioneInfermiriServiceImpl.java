package com.msinfermiere.service.impl;

import com.msinfermiere.dto.infermiere.InfermiereDto;
import com.msinfermiere.dto.request.gestioneinfermiere.AddInfermiereDto;
import com.msinfermiere.dto.request.gestioneinfermiere.FilterInfermiereDto;
import com.msinfermiere.dto.request.gestioneinfermiere.ModificaInfermiereDto;
import com.msinfermiere.dto.specification.InfermiereSpecificationsDto;
import com.msinfermiere.entity.Infermiere;
import com.msinfermiere.entity.Reparto;
import com.msinfermiere.mapper.infermiere.InfermiereDtoMapper;
import com.msinfermiere.mapper.infermiere.InfermiereEntityMapper;
import com.msinfermiere.repository.InfermiereRepository;
import com.msinfermiere.repository.RepartoRepository;
import com.msinfermiere.service.GestioneInfermiriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@Service
public class GestioneInfermiriServiceImpl implements GestioneInfermiriService {
    @Autowired
    private InfermiereRepository infermiereRepository;

    @Autowired
    private RepartoRepository repartoRepository;

    @Override
    public List<InfermiereDto> getAllInfermieri() {
        return InfermiereDtoMapper.INSTANCE.toDto(infermiereRepository.findAll());
    }

    @Override
    public InfermiereDto addInfermiere(AddInfermiereDto addInfermiereDto) {
        Infermiere infermiere = InfermiereEntityMapper.INSTANCE.fromRequest(addInfermiereDto);
        Reparto reparto = repartoRepository.findById(addInfermiereDto.getIdReparto()).orElseThrow(() -> new RuntimeException("Reparto non trovato"));
        infermiere.setReparto(reparto);

        return InfermiereDtoMapper.INSTANCE.toDto(infermiereRepository.save(infermiere));
    }

    @Override
    public List<InfermiereDto> filterInfermieri(FilterInfermiereDto filterInfermiereDto) {
        Specification<Infermiere> spec = Specification.where(null);

        if (filterInfermiereDto.getIdInfermiere() != null) {
            spec = spec.and(InfermiereSpecificationsDto.hasIdInfermiere(filterInfermiereDto.getIdInfermiere()));
        }
        if (filterInfermiereDto.getNomeInfermiere() != null) {
            spec = spec.and(InfermiereSpecificationsDto.hasNomeInfermiere(filterInfermiereDto.getNomeInfermiere()));
        }
        if (filterInfermiereDto.getCognomeInfermiere() != null) {
            spec = spec.and(InfermiereSpecificationsDto.hasCognomeInfermiere(filterInfermiereDto.getCognomeInfermiere()));
        }
        if (filterInfermiereDto.getIdReparto() != null) {
            spec = spec.and(InfermiereSpecificationsDto.hasIdReparto(filterInfermiereDto.getIdReparto()));
        }
        if (filterInfermiereDto.getTurnoInfermiere() != null) {
            spec = spec.and(InfermiereSpecificationsDto.hasTurmaInfermiere(filterInfermiereDto.getTurnoInfermiere()));
        }
        return InfermiereDtoMapper.INSTANCE.toDto(infermiereRepository.findAll(spec));
    }

    @Override
    public InfermiereDto modificaInfermiere(ModificaInfermiereDto modificaInfermiereDto) {
        Infermiere infermiere = infermiereRepository.findById(modificaInfermiereDto.getIdInfermiere())
                .orElseThrow(() -> new IllegalArgumentException("Infermiere non trovato con ID: " + modificaInfermiereDto.getIdInfermiere()));
        if (modificaInfermiereDto.getNuovoNomeInfermiere() != null) {
            infermiere.setNomeInfermiere(modificaInfermiereDto.getNuovoNomeInfermiere());
        }
        if (modificaInfermiereDto.getNuovoCognomeInfermiere() != null) {
            infermiere.setCognomeInfermiere(modificaInfermiereDto.getNuovoCognomeInfermiere());
        }
        if (modificaInfermiereDto.getNuovoIdReparto() != null) {
            Reparto reparto = repartoRepository.findById(modificaInfermiereDto.getNuovoIdReparto())
                    .orElseThrow(() -> new IllegalArgumentException("Infermiere non trovato con ID: " + modificaInfermiereDto.getIdInfermiere()));
            infermiere.setReparto(reparto);
        }
        if (modificaInfermiereDto.getNuovoTurnoInfermiere() != null) {
            infermiere.setTurnoInfermiere(modificaInfermiereDto.getNuovoTurnoInfermiere());
        }
        return InfermiereDtoMapper.INSTANCE.toDto(infermiereRepository.save(infermiere));
    }

    @Override
    public Void deleteInfermiere(Integer idInfermiere) {
        infermiereRepository.deleteById(idInfermiere);
        return null;
    }
}
