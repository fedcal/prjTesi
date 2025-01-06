package com.msinfermiere.service.impl;

import com.msinfermiere.dto.infermiere.CartellaClinicaDto;
import com.msinfermiere.dto.infermiere.ContattoRiferimentoDto;
import com.msinfermiere.dto.infermiere.PazienteDto;
import com.msinfermiere.dto.infermiere.relation.MalattiaCartellaDto;
import com.msinfermiere.dto.infermiere.relation.MedicinaleCartellaDto;
import com.msinfermiere.dto.infermiere.relation.VisitaMedicaCartellaDto;
import com.msinfermiere.dto.request.gestionepaziente.*;
import com.msinfermiere.dto.specification.PazienteSpecificationsDto;
import com.msinfermiere.entity.*;
import com.msinfermiere.entity.relation.MalattiaCartella;
import com.msinfermiere.entity.relation.MedicinaleCartella;
import com.msinfermiere.entity.relation.VisitaMedicaCartella;
import com.msinfermiere.entity.visitamedica.VisitaMedica;
import com.msinfermiere.mapper.cartellaclinica.CartellaClinicaDtoMapper;
import com.msinfermiere.mapper.cartellaclinica.CartellaClinicaEntityMapper;
import com.msinfermiere.mapper.contattoriferimento.ContattoRiferimentoDtoMapper;
import com.msinfermiere.mapper.contattoriferimento.ContattoRiferimentoEntityMapper;
import com.msinfermiere.mapper.paziente.PazienteDtoMapper;
import com.msinfermiere.mapper.paziente.PazienteEntityMapper;
import com.msinfermiere.mapper.relation.malattiacartella.MalattiaCartellaDtoMapper;
import com.msinfermiere.mapper.relation.medicinalecartella.MedicinaleCartellaDtoMapper;
import com.msinfermiere.mapper.relation.visitamedicacartella.VisitaMedicaCartellaDtoMapper;
import com.msinfermiere.repository.*;
import com.msinfermiere.repository.relation.MalattiaCartellaRepository;
import com.msinfermiere.repository.relation.MedicinaleCartellaRepository;
import com.msinfermiere.repository.relation.VisitaMedicaCartellaRepository;
import com.msinfermiere.repository.visitamedica.VisitaMedicaRepository;
import com.msinfermiere.service.GestionePazientiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionePazientiServiceImpl implements GestionePazientiService {
    @Autowired
    private PazienteRepository pazienteRepository;

    @Autowired
    private CartellaClinicaRepository cartellaClinicaRepository;

    @Autowired
    private ContattoRiferimentoRepository contattoRiferimentoRepository;

    @Autowired
    private RepartoRepository repartoRepository;

    @Autowired
    private MalattiaCartellaRepository malattiaCartellaRepository;

    @Autowired
    private MalattiaRepository malattiaRepository;

    @Autowired
    private MedicinaleRepository medicinaleRepository;

    @Autowired
    private MedicinaleCartellaRepository medicinaleCartellaRepository;
    @Autowired
    private VisitaMedicaRepository visitaMedicaRepository;
    @Autowired
    private VisitaMedicaCartellaRepository visitaMedicaCartellaRepository;

    @Override
    public List<PazienteDto> getAllInfermieri() {
        return PazienteDtoMapper.INSTANCE.toDto(pazienteRepository.findAll());
    }

    @Override
    public List<PazienteDto> filterPazienti(FilterPazienteDto filterPazienteDtoDto) {
        Specification<Paziente> spec = Specification.where(null);

        if (filterPazienteDtoDto.getIdPaziente() != null) {
            spec = spec.and(PazienteSpecificationsDto.hasIdPaziente(filterPazienteDtoDto.getIdPaziente()));
        }

        if (filterPazienteDtoDto.getNomePaziente() != null) {
            spec = spec.and(PazienteSpecificationsDto.hasNomePaziente(filterPazienteDtoDto.getNomePaziente()));
        }

        if (filterPazienteDtoDto.getCognomePaziente() != null) {
            spec = spec.and(PazienteSpecificationsDto.hasCognomePaziente(filterPazienteDtoDto.getCognomePaziente()));
        }

        if (filterPazienteDtoDto.getIdReparto() != null) {
            spec = spec.and(PazienteSpecificationsDto.hasIdReparto(filterPazienteDtoDto.getIdReparto()));
        }

        if (filterPazienteDtoDto.getDataNascita() != null) {
            spec = spec.and(PazienteSpecificationsDto.hasDataNascita(filterPazienteDtoDto.getDataNascita()));
        }

        if (filterPazienteDtoDto.getLuogoNascita() != null) {
            spec = spec.and(PazienteSpecificationsDto.hasLuogoNascita(filterPazienteDtoDto.getLuogoNascita()));
        }

        if (filterPazienteDtoDto.getProvinciaNascita() != null) {
            spec = spec.and(PazienteSpecificationsDto.hasProvinciaNascita(filterPazienteDtoDto.getProvinciaNascita()));
        }

        if (filterPazienteDtoDto.getContattoRiferimento() != null) {
            spec = spec.and(PazienteSpecificationsDto.hasContattoRiferimento(filterPazienteDtoDto.getContattoRiferimento()));
        }
        return PazienteDtoMapper.INSTANCE.toDto(pazienteRepository.findAll(spec));
    }

    @Override
    public CartellaClinicaDto addCartellaClinica(AddCartellaClinicaDto addCartellaClinicaDto) {
        CartellaClinica cartellaClinica = CartellaClinicaEntityMapper.INSTANCE.toEntity(addCartellaClinicaDto);

        return CartellaClinicaDtoMapper.INSTANCE.toDto(cartellaClinicaRepository.save(cartellaClinica));
    }

    @Override
    public ContattoRiferimentoDto addContattoRiferimento(AddContattoRiferimentoDto addContattoRiferimentoDto) {
        ContattoRiferimento contattoRiferimento = ContattoRiferimentoEntityMapper.INSTANCE.toEntity(addContattoRiferimentoDto);
        return ContattoRiferimentoDtoMapper.INSTANCE.toDto(contattoRiferimentoRepository.save(contattoRiferimento));
    }

    @Override
    public PazienteDto addPaziente(AddPazientereDto addPazientereDto) {
        ContattoRiferimento contattoRiferimento = contattoRiferimentoRepository.findById(addPazientereDto.getIdContattoRiferimento()).get();
        Reparto reparto = repartoRepository.findById(addPazientereDto.getIdReparto()).get();
        CartellaClinica cartellaClinica = cartellaClinicaRepository.findById(addPazientereDto.getIdCartellaClinica()).get();
        Paziente paziente = PazienteEntityMapper.INSTANCE.toEntity(addPazientereDto);
        paziente.setCartellaClinica(cartellaClinica);
        paziente.setReparto(reparto);
        paziente.setContattoRiferimento(contattoRiferimento);

        return PazienteDtoMapper.INSTANCE.toDto(pazienteRepository.save(paziente));
    }

    @Override
    public MalattiaCartellaDto collegaMalattiaPaziente(CollegaMalattiaCartellaDto collegaMalattiaCartellaDto) {
        MalattiaCartella malattiaCartella = new MalattiaCartella();
        CartellaClinica cartellaClinica = cartellaClinicaRepository.findById(collegaMalattiaCartellaDto.getIdCartellaClinica()).get();
        Malattia malattia = malattiaRepository.findById(collegaMalattiaCartellaDto.getIdMalattiaPaziente()).get();

        malattiaCartella.setCartellaClinica(cartellaClinica);
        malattiaCartella.setMalattia(malattia);

        return MalattiaCartellaDtoMapper.INSTANCE.toDto(malattiaCartellaRepository.save(malattiaCartella));
    }

    @Override
    public MedicinaleCartellaDto collegaMedicinaleCartellaClinica(CollegaMedicinaleCartellaClinica collegaMedicinaleCartellaClinica) {
        CartellaClinica cartellaClinica = cartellaClinicaRepository.findById(collegaMedicinaleCartellaClinica.getIdCartellaClinica()).get();
        Medicinale medicinale = medicinaleRepository.findById(collegaMedicinaleCartellaClinica.getIdMedicinale()).get();
        MedicinaleCartella medicinaleCartella = new MedicinaleCartella();

        medicinaleCartella.setCartellaClinica(cartellaClinica);
        medicinaleCartella.setMedicinale(medicinale);

        return MedicinaleCartellaDtoMapper.INSTANCE.toDto(medicinaleCartellaRepository.save(medicinaleCartella));
    }

    @Override
    public VisitaMedicaCartellaDto collegaVisitaMedicaCartellaClinica(CollegaVisitaMedicaCartellaDto collegaVisitaMedicaCartellaDto) {
        CartellaClinica cartellaClinica = cartellaClinicaRepository.findById(collegaVisitaMedicaCartellaDto.getIdCartellaClinica()).get();
        VisitaMedica visitaMedica = visitaMedicaRepository.findById(collegaVisitaMedicaCartellaDto.getIdVisitaMedica()).get();
        VisitaMedicaCartella visitaMedicaCartella = new VisitaMedicaCartella();

        visitaMedicaCartella.setVisitaMedica(visitaMedica);
        visitaMedicaCartella.setCartellaClinica(cartellaClinica);

        return VisitaMedicaCartellaDtoMapper.INSTANCE.toDto(visitaMedicaCartellaRepository.saveAndFlush(visitaMedicaCartella));
    }

    @Override
    public CartellaClinicaDto visualizzaCartellaClinica(Integer idCartellaClinica) {
        return CartellaClinicaDtoMapper.INSTANCE.toDto(cartellaClinicaRepository.getOne(idCartellaClinica));
    }
}
