package com.filemanagement.service.impl;

import com.filemanagement.dto.CartelleDto;
import com.filemanagement.dto.params.cartella.CartellaParams;
import com.filemanagement.dto.params.cartella.ModificaCartellaParams;
import com.filemanagement.entity.Cartelle;
import com.filemanagement.esito.EsitoMessaggiRequestContextHolder;
import com.filemanagement.esito.constants.EsitoOperazioneEnum;
import com.filemanagement.mapper.cartelle.CartelleEntityMapper;
import com.filemanagement.repository.CartelleRepository;
import com.filemanagement.service.CartelleService;
import lombok.AllArgsConstructor;
import com.filemanagement.exception.EsitoRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@AllArgsConstructor
@Validated
public class CartelleServiceImpl implements CartelleService {

    private final CartelleRepository cartelleRepository;
    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;

    @Override
    public String aggiungiCartella(CartellaParams cartellaParams) {
        esitoMessaggiRequestContextHolder.setOperationId("aggiungiCartella");
        Optional<Cartelle> cartellaFind = cartelleRepository.findByNomeCartella(cartellaParams.getNomeCartella());
        if(cartellaFind.isPresent()){
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Cartella già presente");
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
                esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
                esitoMessaggiRequestContextHolder.setOperationId("Non è stato possibile creare la nuova cartella nel percorso indicato");
                throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
            }

            CartelleDto cartelleDto = new CartelleDto();
            cartelleDto.setNomeCartella(cartellaParams.getNomeCartella());
            cartelleDto.setPath(cartellaParams.getPath());

            Cartelle cartellaSaved = cartelleRepository.save(CartelleEntityMapper.INSTANCE.toEntity(cartelleDto));

            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
            if (cartellaCreata && cartellaSaved != null) {
                return "Cartella creata correttamente e salvata nel Db";
            } else if (cartellaCreata) {
                return "Cartella creata correttamente, ma non è stato poossibile salvarla nel db";
            } else if (cartellaSaved != null) {
                return "Cartella salvata nel db, ma non è stato possibile crearla";
            }
        }
        return "";
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
            esitoMessaggiRequestContextHolder.setOperationId("Specificare sia il path sia il nome della cartella");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }else if(cartellaParams.getPath()==null && cartellaParams.getNomeCartella()!=null){
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Specificare sia il path sia il nome della cartella");
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
    public String rinominaCartella(ModificaCartellaParams cartellaParams) {
        String messaggio = "Cartella rinominata correttamente";

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
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Non è stato possibile creare la nuova cartella nel percorso indicato");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }
        
        //Inserimento nuova cartella nel db

        Cartelle nuovaCartellaDb = new Cartelle();
        nuovaCartellaDb.setNomeCartella(cartellaParams.getNuovoNomeCartella());
        nuovaCartellaDb.setPath(cartellaParams.getNuovoPath());
        try {
            cartelleRepository.save(nuovaCartellaDb);
        }catch (Exception e){
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Non è stato possibile salvare la cartella nel db");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        //Copia dei file nella nuova cartella
        try {
            FileUtils.copyDirectory(new File(vecchioPath), new File(nuovoPath));
        }catch (IOException e){
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Non è stato possibile copiare i file dalla vecchia cartella a quella nuova");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }


        //Eliminare la vecchia cartella
        try {
            FileUtils.deleteDirectory(new File(vecchioPath));
        }catch (IOException e){
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Non è stato possibile eliminare la vecchia cartella");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }


        //Eliminare la vecchia cartella dal db
        try {
            cartelleRepository.deleteCartelleByNomeCartellaAndPath(cartellaParams.getNomeCartella(),cartellaParams.getPath());
        }catch (Exception e){
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Non è stato possibile eliminare la vecchia cartella nel db");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
        return messaggio;
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
}
