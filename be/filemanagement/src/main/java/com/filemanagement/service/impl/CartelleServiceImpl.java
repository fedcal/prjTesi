package com.filemanagement.service.impl;

import com.filemanagement.dto.CartelleDto;
import com.filemanagement.dto.params.CartellaParams;
import com.filemanagement.dto.params.ModificaCartellaParams;
import com.filemanagement.entity.Cartelle;
import com.filemanagement.mapper.cartelle.CartelleEntityMapper;
import com.filemanagement.repository.CartelleRepository;
import com.filemanagement.service.CartelleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Optional;

@Service
public class CartelleServiceImpl implements CartelleService {
    private final CartelleRepository cartelleRepository;

    public CartelleServiceImpl(CartelleRepository cartelleRepository){
        this.cartelleRepository = cartelleRepository;
    }

    @Override
    public String aggiungiCartella(CartellaParams cartellaParams) {
        Optional<Cartelle> cartellaFind = cartelleRepository.findByNomeCartella(cartellaParams.getNomeCartella());
        if(cartellaFind.isPresent()){
            return "Cartella già presente";
        }else {
            boolean cartellaCreata = new File(cartellaParams.getPath() + "\\" + cartellaParams.getNomeCartella()).mkdirs();

            CartelleDto cartelleDto = new CartelleDto();
            cartelleDto.setNomeCartella(cartellaParams.getNomeCartella());
            cartelleDto.setPath(cartellaParams.getPath());

            Cartelle cartellaSaved = cartelleRepository.save(CartelleEntityMapper.INSTANCE.toEntity(cartelleDto));

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
        String messg = checkParams(cartellaParams);

        if(cartellaParams.getPath()!=null && cartellaParams.getNomeCartella()!=null){
            deleteFiles(cartellaParams);
            cartelleRepository.deleteCartelleByNomeCartella(cartellaParams.getNomeCartella());
            messg = "File eliminati";
        }if(cartellaParams.getPath()!=null && cartellaParams.getNomeCartella()==null){
            return "Specificare sia il path sia il nome della cartella";
        }else if(cartellaParams.getPath()==null && cartellaParams.getNomeCartella()!=null){
            return "Specificare sia il path sia il nome della cartella";
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

    private String checkParams(CartellaParams cartellaParams) {
        if(cartellaParams.getNomeCartella()==null && cartellaParams.getPath()==null){
            return "Inserire almeno un parametro";
        }else {
            return "";
        }
    }

    @Override
    public String rinominaCartella(ModificaCartellaParams cartellaParams) {
        String messaggio = checkRinominaCartellaParamms(cartellaParams);

        String nuovoPath = cartellaParams.getPath() + "/" + cartellaParams.getNomeCartella();


        return messaggio;
    }

    private String checkRinominaCartellaParamms(ModificaCartellaParams cartellaParams) {
        boolean paramsNull = cartellaParams.getNomeCartella()==null || cartellaParams.getNuovoNomeCartella()==null
                || cartellaParams.getPath()==null;
        if(cartellaParams == null || paramsNull){
            return "Inserire tutti i parametri richiesti";
        }else{
            return "";
        }
    }
}
