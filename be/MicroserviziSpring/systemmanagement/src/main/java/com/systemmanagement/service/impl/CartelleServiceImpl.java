package com.systemmanagement.service.impl;

import com.systemmanagement.dto.CartelleDto;
import com.systemmanagement.dto.params.cartella.CartellaParams;
import com.systemmanagement.dto.params.cartella.ModificaCartellaParams;
import com.systemmanagement.entity.Cartelle;
import com.systemmanagement.esito.EsitoMessaggiRequestContextHolder;
import com.systemmanagement.esito.Messaggio;
import com.systemmanagement.esito.constants.EsitoOperazioneEnum;
import com.systemmanagement.esito.constants.SeveritaMessaggioEnum;
import com.systemmanagement.mapper.cartelle.CartelleDtoMapper;
import com.systemmanagement.mapper.cartelle.CartelleEntityMapper;
import com.systemmanagement.repository.CartelleRepository;
import com.systemmanagement.service.CartelleService;
import lombok.AllArgsConstructor;
import com.systemmanagement.exception.EsitoRuntimeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Validated
public class CartelleServiceImpl implements CartelleService {

    private final CartelleRepository cartelleRepository;
    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;

    @Override
    public CartelleDto creaCartella(CartellaParams cartellaParams) {
        esitoMessaggiRequestContextHolder.setOperationId("creaCartella");
        Optional<Cartelle> cartellaFind = cartelleRepository.findByNomeCartella(cartellaParams.getNomeCartella());
        if(cartellaFind.isPresent()){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Cartella già presente.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }else {
            String path = "";
            String osName = System.getProperty("os.name");
            if(osName.contains("Wind") || osName.contains("wind")){
                cartellaParams.setPath(cartellaParams.getPath().replace("\\\\","\\"));
                path = cartellaParams.getPath() + "\\" + cartellaParams.getNomeCartella();
            } else {
                path = cartellaParams.getPath() + "/" + cartellaParams.getNomeCartella();
            }
            boolean cartellaCreata = new File(path).mkdirs();

            if(!cartellaCreata){
                Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è stato possibile creare la nuova cartella nel percorso indicato").build();
                esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
                esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
                throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
            }

            CartelleDto cartelleDto = new CartelleDto();
            cartelleDto.setNomeCartella(cartellaParams.getNomeCartella());
            cartelleDto.setPath(cartellaParams.getPath());
            cartelleDto.setIsCartellaAddestramento(cartellaParams.getIsCartellaAddestramento());

            Cartelle cartellaSaved = cartelleRepository.save(CartelleEntityMapper.INSTANCE.toEntity(cartelleDto));

            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
            if (cartellaCreata && cartellaSaved != null) {
                Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Cartella creata correttamente e salvata nel Db").build();
                esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
                return cartelleDto;
            } else if (cartellaCreata) {
                Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Cartella creata correttamente, ma non è stato poossibile salvarla nel db").build();
                esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
                return cartelleDto;
            } else if (cartellaSaved != null) {
                Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Cartella creata correttamente, ma non è stato poossibile salvarla nel db").build();
                esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
                return cartelleDto;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public String eliminaCartella(CartellaParams cartellaParams) {
        esitoMessaggiRequestContextHolder.setOperationId("eliminaCartella");
        String messg = "";

        if(cartellaParams.getPath()!=null && cartellaParams.getNomeCartella()!=null){
            deleteFiles(cartellaParams);
            cartelleRepository.deleteCartelleByNomeCartella(cartellaParams.getNomeCartella());
            messg = "File eliminati";
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
        }if(cartellaParams.getPath()!=null && cartellaParams.getNomeCartella()==null){
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Specificare sia il path sia il nome della cartella").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }else if(cartellaParams.getPath()==null && cartellaParams.getNomeCartella()!=null){
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Specificare sia il path sia il nome della cartella").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        return messg;
    }

    private void deleteFiles(CartellaParams cartellaParams) {
        File[] allContents = new File(cartellaParams.getPath()+"\\"+cartellaParams.getNomeCartella()).listFiles();

        if (allContents!=null){
            for (File file : allContents){
                deleteFilesRecursive(file);
            }
        }
        String path = "";
        String osName = System.getProperty("os.name");
        if(osName.contains("Wind") || osName.contains("wind")){
            path = cartellaParams.getPath()+"\\"+cartellaParams.getNomeCartella();
        } else {
            path = cartellaParams.getPath()+"/"+cartellaParams.getNomeCartella();
        }
        new File(path).delete();
    }

    private boolean deleteFilesRecursive(File files) {
        File[] allContents = files.listFiles();

        if (allContents!=null){
            for (File file : allContents){
                deleteFilesRecursive(file);
            }
        }
        return files.delete();
    }

    @Override
    @Transactional
    public CartelleDto rinominaCartella(ModificaCartellaParams cartellaParams) {
        esitoMessaggiRequestContextHolder.setOperationId("rinominaCartella");
        checkFolderAlreadyExists(cartellaParams.getNuovoNomeCartella(),cartellaParams.getNuovoPath());
        checkOldFolderDoesntExist(cartellaParams.getNomeCartella(),cartellaParams.getPath());

        String vecchioPath = "";
        String nuovoPath = "";
        String osName = System.getProperty("os.name");
        if(osName.contains("Wind") || osName.contains("wind")){
            vecchioPath = cartellaParams.getPath() + "\\" + cartellaParams.getNomeCartella();
            nuovoPath = cartellaParams.getNuovoPath() + "\\" + cartellaParams.getNuovoNomeCartella();
        } else {
            vecchioPath = cartellaParams.getPath() + "/" + cartellaParams.getNomeCartella();
            nuovoPath = cartellaParams.getNuovoPath() + "/" + cartellaParams.getNuovoNomeCartella();
        }

        //Creazione della nuova cartella
        boolean nuovaCartella = new File(nuovoPath).mkdirs();
        if(!nuovaCartella){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è stato possibile creare la nuova cartella nel percorso indicato").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }
        
        //Inserimento nuova cartella nel db

        Cartelle nuovaCartellaDb = new Cartelle();
        nuovaCartellaDb.setNomeCartella(cartellaParams.getNuovoNomeCartella());
        nuovaCartellaDb.setPath(cartellaParams.getNuovoPath());
        try {
            cartelleRepository.save(nuovaCartellaDb);
        }catch (Exception e){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è stato possibile salvare la cartella nel db").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        //Copia dei file nella nuova cartella
        try {
            FileUtils.copyDirectory(new File(vecchioPath), new File(nuovoPath));
        }catch (IOException e){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è stato possibile copiare i file dalla vecchia cartella a quella nuova").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }


        //Eliminare la vecchia cartella
        try {
            FileUtils.deleteDirectory(new File(vecchioPath));
        }catch (IOException e){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è stato possibile eliminare la vecchia cartella").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }


        //Eliminare la vecchia cartella dal db
        try {
            cartelleRepository.deleteCartelleByNomeCartellaAndPath(cartellaParams.getNomeCartella(),cartellaParams.getPath());
        }catch (Exception e){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è stato possibile eliminare la vecchia cartella nel db").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
        return CartelleDtoMapper.INSTANCE.toDto(nuovaCartellaDb);
    }

    @Override
    public List<CartelleDto> elencoCartelle() {
        esitoMessaggiRequestContextHolder.setOperationId("elencoCartelle");
        List<Cartelle> cartelleFind = cartelleRepository.findAll();
        List<CartelleDto> cartelleDtoList = null;

        if(!cartelleFind.isEmpty()){
            cartelleDtoList = CartelleDtoMapper.INSTANCE.toDtos(cartelleFind);
        }else{
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è presente nessuna cartella.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
        return cartelleDtoList;
    }

    private void checkOldFolderDoesntExist(String nomeCartella, String path) {
        Optional<Cartelle> findCartella = cartelleRepository.findByNomeCartellaAndPath(nomeCartella,path);
        if(!findCartella.isPresent()) {
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Non esiste una cartella denominata: " + nomeCartella);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }
    }

    private void checkFolderAlreadyExists(String nomeCartella, String path) {
        Optional<Cartelle> findCartella = cartelleRepository.findByNomeCartellaAndPath(nomeCartella,path);
        if(findCartella.isPresent()) {
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Esiste già una cartella denominata: " + nomeCartella);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public CartelleDto findCartella(CartellaParams cartellaParams) {
        CartelleDto findCartellaDto = null;
        Optional<Cartelle> findCartella = Optional.empty();
        esitoMessaggiRequestContextHolder.setOperationId("findCartella");

        if(!StringUtils.isBlank(cartellaParams.getNomeCartella()) && !StringUtils.isBlank(cartellaParams.getPath())){
            findCartella = cartelleRepository.findByNomeCartellaAndPath(cartellaParams.getNomeCartella(), cartellaParams.getPath());
        } else if (!StringUtils.isBlank(cartellaParams.getNomeCartella()) ) {
            findCartella = cartelleRepository.findByNomeCartella(cartellaParams.getNomeCartella());
        }

        if(findCartella.isEmpty()){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è presente nessuna cartella.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }else{
            findCartellaDto = CartelleDtoMapper.INSTANCE.toDto(findCartella.get());
        }

        esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
        return findCartellaDto;
    }

    @Override
    public CartelleDto aggiungiCartella(CartellaParams cartellaParams) {
        esitoMessaggiRequestContextHolder.setOperationId("aggiungiCartella");
        Optional<Cartelle> cartellaFind = cartelleRepository.findByNomeCartella(cartellaParams.getNomeCartella());
        if(cartellaFind.isPresent()){
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Cartella già presente.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }else {
            CartelleDto cartelleDto = new CartelleDto();
            cartelleDto.setNomeCartella(cartellaParams.getNomeCartella());
            cartelleDto.setPath(cartellaParams.getPath());
            cartelleDto.setIsCartellaAddestramento(cartellaParams.getIsCartellaAddestramento());

            Cartelle cartellaSaved = cartelleRepository.save(CartelleEntityMapper.INSTANCE.toEntity(cartelleDto));

            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
            if (cartellaSaved != null) {
                Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Cartella creata correttamente, ma non è stato poossibile salvarla nel db").build();
                esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
                return cartelleDto;
            }
        }
        return null;
    }
}
