package com.systemmanagement.service.impl;

import com.systemmanagement.dto.RagBotPdfDto;
import com.systemmanagement.dto.params.ragbotpdf.ModificaBotParams;
import com.systemmanagement.dto.params.ragbotpdf.RegistraBotParams;
import com.systemmanagement.dto.params.ragbotpdf.TrovaBotParams;
import com.systemmanagement.entity.Cartelle;
import com.systemmanagement.entity.RagBotPdf;
import com.systemmanagement.esito.EsitoMessaggiRequestContextHolder;
import com.systemmanagement.esito.Messaggio;
import com.systemmanagement.esito.constants.EsitoOperazioneEnum;
import com.systemmanagement.esito.constants.SeveritaMessaggioEnum;
import com.systemmanagement.exception.EsitoRuntimeException;
import com.systemmanagement.mapper.ragbotpdf.RagBotPdfDtoMapper;
import com.systemmanagement.repository.CartelleRepository;
import com.systemmanagement.repository.RagBotPdfRepository;
import com.systemmanagement.service.RagBotPdfService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RagBotPdfServiceImpl implements RagBotPdfService {

    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;
    private final RagBotPdfRepository ragBotPdfRepository;
    private final CartelleRepository cartelleRepository;

    @Override
    public List<RagBotPdfDto> elencoBot() {
        esitoMessaggiRequestContextHolder.setOperationId("elencoBot");

        List<RagBotPdf> listBot = ragBotPdfRepository.findAll();

        if(listBot.isEmpty()){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è presente nessun bot.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        List<RagBotPdfDto> listConverted = RagBotPdfDtoMapper.INSTANCE.toDtos(listBot);

        esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
        return listConverted;
    }

    @Override
    public RagBotPdfDto registraBot(RegistraBotParams registraBotParams) {
        esitoMessaggiRequestContextHolder.setOperationId("registraBot");
        //TODO checkParam
        checkParams(registraBotParams);
        checkBotAlreadyExists(registraBotParams.getNomeBot());

        Cartelle cartellaAddestramento = getCartellaAddestramento(registraBotParams.getNomeCartellaAddestramento());
        Cartelle cartellaCaricamentoMassivo = getCartellaCaricamentoMassivo(registraBotParams.getNomeCartellaCaricamentoMassivo());

        RagBotPdf botToSave = new RagBotPdf();
        botToSave.setNomeBot(registraBotParams.getNomeBot());
        botToSave.setDescrizioneBot(registraBotParams.getDescrizioneBot());
        botToSave.setUrlPython(registraBotParams.getUrlPython());
        botToSave.setUrlSpring(registraBotParams.getUrlSpring());
        botToSave.setCartellaAddestramento(cartellaAddestramento);
        botToSave.setCartellaCaricamentoMassivo(cartellaCaricamentoMassivo);

        try {
            ragBotPdfRepository.save(botToSave);
        }catch (Exception e){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Nessuna configurazione del bot salvata.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
        return RagBotPdfDtoMapper.INSTANCE.toDto(botToSave);
    }

    private void checkBotAlreadyExists(String nomeBot) {
        Optional<RagBotPdf> ragBotPdfFind = ragBotPdfRepository.findByNomeBot(nomeBot);
        if(ragBotPdfFind.isPresent()){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Configurazione bot già presente.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }
    }

    private void checkParams(RegistraBotParams registraBotParams) {
        boolean errorParams = false;

        if(StringUtils.isBlank(registraBotParams.getNomeBot())){
            errorParams = true;
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Specificare il nome del bot.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
        }

        if(StringUtils.isBlank(registraBotParams.getUrlPython())){
            errorParams = true;
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Specificare l'url del servizio di python.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
        }

        if(StringUtils.isBlank(registraBotParams.getNomeBot())){
            errorParams = true;
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Specificare l'url del servizio di springboot.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
        }

        if(StringUtils.isBlank(registraBotParams.getNomeCartellaAddestramento())){
            errorParams = true;
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Specificare la cartella di addestramento del bot.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
        }

        if(errorParams){
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }
    }

    private Cartelle getCartellaCaricamentoMassivo(String nomeCartellaCaricamentoMassivo) {
        Optional<Cartelle> cartellaCariccamentoMassivo = cartelleRepository.findByNomeCartella(nomeCartellaCaricamentoMassivo);

        if(cartellaCariccamentoMassivo.isEmpty()){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è presente nessuna cartella di caricamento massivo.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }else{
            return cartellaCariccamentoMassivo.get();
        }
    }

    private Cartelle getCartellaAddestramento(String nomeCartellaAddestramento) {
        Optional<Cartelle> cartellaCariccamentoMassivo = cartelleRepository.findByNomeCartella(nomeCartellaAddestramento);

        if(cartellaCariccamentoMassivo.isEmpty()){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è presente nessuna cartella di addestramento relativa al bot.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }else{
            return cartellaCariccamentoMassivo.get();
        }
    }

    @Override
    @Transactional
    public RagBotPdfDto modificaBot(ModificaBotParams modificaBotParams) {
        esitoMessaggiRequestContextHolder.setOperationId("modificaBot");
        //TODO CHECK PARAM
        Optional<RagBotPdf> findBot = ragBotPdfRepository.findByNomeBot(modificaBotParams.getNomeBot());

        if(findBot.isEmpty()){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è stato trovato nessun bot.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        modifyBot(findBot,modificaBotParams);

        try{
            ragBotPdfRepository.save(findBot.get());
        }catch (Exception e){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è stato possibile salvare la nuova configurazione del bot.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
        return null;
    }

    private void modifyBot(Optional<RagBotPdf> findBot, ModificaBotParams modificaBotParams) {
        if(!StringUtils.isBlank(modificaBotParams.getNuovoNomeBot())){
            findBot.get().setNomeBot(modificaBotParams.getNuovoNomeBot().trim());
        }

        if(!StringUtils.isBlank(modificaBotParams.getNuovaDescrizioneBot())){
            findBot.get().setDescrizioneBot(modificaBotParams.getNuovaDescrizioneBot().trim());
        }
        if(!StringUtils.isBlank(modificaBotParams.getNuovoUrlPython())){
            findBot.get().setUrlPython(modificaBotParams.getNuovoUrlPython().trim());
        }
        if(!StringUtils.isBlank(modificaBotParams.getNuovoUrlSpring())){
            findBot.get().setUrlSpring(modificaBotParams.getNuovoUrlSpring().trim());
        }
        if(!StringUtils.isBlank(modificaBotParams.getNuovoNomeCartellaAddestramento())){
            Optional<Cartelle> cartellaAddestramentoFined = cartelleRepository.findByNomeCartella(modificaBotParams.getNuovoNomeCartellaAddestramento().trim());
            if(cartellaAddestramentoFined.isEmpty()){
                Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è stato trovato nessuna cartella di addestramento.").build();
                esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
                esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
                throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
            }else{
                findBot.get().setCartellaAddestramento(cartellaAddestramentoFined.get());
            }
        }
        if(!StringUtils.isBlank(modificaBotParams.getNuovoNomeCartellaCaricamentoMassivo())){
            Optional<Cartelle> cartellaCaricamentoMassivoFind = cartelleRepository.findByNomeCartella(modificaBotParams.getNuovoNomeCartellaCaricamentoMassivo().trim());
            if(cartellaCaricamentoMassivoFind.isEmpty()){
                Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è stato trovato nessuna cartella di caricamento file massivi.").build();
                esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
                esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
                throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
            }else{
                findBot.get().setCartellaCaricamentoMassivo(cartellaCaricamentoMassivoFind.get());
            }
        }
    }

    @Override
    @Transactional
    public String eliminaBot(String nomeBot) {
        String eliminato = "";
        esitoMessaggiRequestContextHolder.setOperationId("eliminaBot");

        try{
            ragBotPdfRepository.deleteBotRagByName(nomeBot);
            eliminato = "Bot eliminato";
        }catch (Exception e){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è stato possibile.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
        return eliminato;
    }

    @Override
    public RagBotPdfDto trovaBot(TrovaBotParams trovaBotParam) {
        checkParams(trovaBotParam);
        Optional<RagBotPdf> findBot = Optional.empty();
        if(trovaBotParam.getIdBot() != 0 && !StringUtils.isBlank(trovaBotParam.getNomeBot())){
            findBot = ragBotPdfRepository.findByIdAndNomeBot(trovaBotParam.getNomeBot().trim(), Long.valueOf(trovaBotParam.getIdBot()));
        } else if (trovaBotParam.getIdBot() != 0 && StringUtils.isBlank(trovaBotParam.getNomeBot())) {
            findBot = ragBotPdfRepository.findById(trovaBotParam.getIdBot());
        } else if (trovaBotParam.getIdBot() == 0 && !StringUtils.isBlank(trovaBotParam.getNomeBot())) {
            findBot = ragBotPdfRepository.findByNomeBot(trovaBotParam.getNomeBot().trim());
        }

        if(findBot.isEmpty()){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Bot non trovato.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.NOT_FOUND);
        }


        return RagBotPdfDtoMapper.INSTANCE.toDto(findBot.get());
    }

    private void checkParams(TrovaBotParams trovaBotParam) {
        if(trovaBotParam.getIdBot() == 0 && StringUtils.isBlank(trovaBotParam.getNomeBot())){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Inserire un elemento di ricerca.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }
    }
}
