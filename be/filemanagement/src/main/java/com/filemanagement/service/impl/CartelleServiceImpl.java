package com.filemanagement.service.impl;

import com.filemanagement.dto.CartelleDto;
import com.filemanagement.dto.params.CartellaParams;
import com.filemanagement.dto.params.ModificaCartellaParams;
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
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Optional;

@Service
@AllArgsConstructor
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
            boolean cartellaCreata = new File(cartellaParams.getPath() + "\\" + cartellaParams.getNomeCartella()).mkdirs();
            //TODO Implementare check cartella creata

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
        new File(cartellaParams.getPath()+"\\"+cartellaParams.getNomeCartella()).delete();
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
    public String rinominaCartella(ModificaCartellaParams cartellaParams) {
        String messaggio = "Cartella rinominata correttamente";

        checkFolderAlreadyExists(cartellaParams.getNuovoNomeCartella(),cartellaParams.getNuovoPath());
        checkOldFolderDoesntExist(cartellaParams.getNomeCartella(),cartellaParams.getPath());

        String vecchioPath = cartellaParams.getPath() + "/" + cartellaParams.getNomeCartella();
        String nuovoPath = cartellaParams.getNuovoPath() + "/" + cartellaParams.getNuovoNomeCartella();

        Path sourceDir = Paths.get(vecchioPath);
        Path targetDir = Paths.get(nuovoPath);

        //Creazione della nuova cartella
        boolean nuovaCartella = new File(cartellaParams.getPath() + "\\" + cartellaParams.getNomeCartella()).mkdirs();
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
            Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path targetDir = null;

                    targetDir = targetDir.resolve(sourceDir.relativize(dir));

                    if (!Files.exists(targetDir)) {
                        Files.createDirectory(targetDir);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.copy(file, targetDir.resolve(sourceDir.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
                    return FileVisitResult.CONTINUE;
                }
            });
        }catch (IOException e){
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Non è stato possibile copiare i file dalla vecchia cartella a quella nuova");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }


        //Eliminare la vecchia cartella
        try {
            Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        }catch (IOException e){
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Non è stato possibile eliminare la vecchia cartella");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }


        //Eliminare la vecchia cartella dal db
        try {
            cartelleRepository.deleteCartelleByNomeCartellaAndPath(cartellaParams.getNomeCartella(),cartellaParams.getNuovoPath());
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
