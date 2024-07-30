package com.filemanagement.service.impl;

import com.filemanagement.dto.params.documenti.ModificaDocumentiParams;
import com.filemanagement.entity.Documenti;
import com.filemanagement.esito.EsitoMessaggiRequestContextHolder;
import com.filemanagement.esito.constants.EsitoOperazioneEnum;
import com.filemanagement.exception.EsitoRuntimeException;
import com.filemanagement.repository.DocumentiRepository;
import com.filemanagement.service.DocumentiService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DocumentiServiceImpl implements DocumentiService {

    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;
    private final DocumentiRepository documentiRepository;

    @Override
    public String uploadFile(MultipartFile file, String uploadDir) {
        esitoMessaggiRequestContextHolder.setOperationId("uploadFile");
        if (file.isEmpty()) {
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Per favore, selezionare un file da caricare.");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadDir + File.separator + file.getOriginalFilename());
            Files.write(path, bytes);

            String fileName = "";
            String[] fileNameSplit = file.getOriginalFilename().split("\\.");
            for(int i=0; i<fileNameSplit.length-1; i++){
                fileName += fileNameSplit[i];
            }

            //Salvare file su db
            Documenti documentoToSave = new Documenti();
            documentoToSave.setNomeDocumento(fileName);
            String[] divisioneEstensione = file.getOriginalFilename().split("\\.");
            documentoToSave.setEstensioneDocumento(divisioneEstensione[divisioneEstensione.length-1]);
            documentoToSave.setPath(uploadDir.replace("\\\\","\\"));
            documentiRepository.save(documentoToSave);

            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
            return "Hai caricato correttamente il file '" + file.getOriginalFilename() + "'";

        } catch (IOException e) {
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Caricamento del file non riuscito correttamente.");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public String deleteFile(String nameFile, String uploadDir) {
        return null;
    }

    @Override
    @Transactional
    public String rinominaFile(ModificaDocumentiParams modificaDocumenti) {
        esitoMessaggiRequestContextHolder.setOperationId("rinominaFile");

        checkParams(modificaDocumenti);
        Documenti documentoDb = checkEsistenzaFile(modificaDocumenti);

        //TODO rinominare file su file system
        File originalFile = null;
        File renameFile = null;
        String osName = System.getProperty("os.name");
        if(osName.contains("Wind") || osName.contains("wind")){
            originalFile = new File(modificaDocumenti.getPathFile()+"\\"+modificaDocumenti.getNomeFile()+"."+documentoDb.getEstensioneDocumento());
            renameFile = new File(modificaDocumenti.getPathFile()+"\\"+modificaDocumenti.getNuovoNome()+"."+documentoDb.getEstensioneDocumento());
        }else{
            originalFile = new File(modificaDocumenti.getPathFile()+"/"+modificaDocumenti.getNomeFile()+"."+documentoDb.getEstensioneDocumento());
            renameFile = new File(modificaDocumenti.getPathFile()+"/"+modificaDocumenti.getNuovoNome()+"."+documentoDb.getEstensioneDocumento());
        }

        boolean renameChek = originalFile.renameTo(renameFile);
        if(!renameChek){
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Il file non è stato rinominato correttamente.");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        //TODO rinominare file su db
        documentiRepository.rinominaFile(modificaDocumenti.getNuovoNome(),modificaDocumenti.getNomeFile(),modificaDocumenti.getPathFile());


        esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
        return null;
    }

    private Documenti checkEsistenzaFile(ModificaDocumentiParams modificaDocumenti) {
        Optional<Documenti> documentoFind = documentiRepository.findDocumentiByNomeDocumentoAndPath(modificaDocumenti.getNomeFile(), modificaDocumenti.getPathFile());
        if(!documentoFind.isPresent()){
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Non è stato possibile recuperare il file dal db.");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        File originalFile = null;
        String osName = System.getProperty("os.name");
        if(osName.contains("Wind") || osName.contains("wind")){
            originalFile = new File(modificaDocumenti.getPathFile()+"\\"+modificaDocumenti.getNomeFile()+"."+documentoFind.get().getEstensioneDocumento());
        }else{
            originalFile = new File(modificaDocumenti.getPathFile()+"/"+modificaDocumenti.getNomeFile()+"."+documentoFind.get().getEstensioneDocumento());
        }

        if(!originalFile.exists()) {
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Non è stato possibile recuperare il file indicato dal file system.");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        return documentoFind.get();
    }

    private void checkParams(ModificaDocumentiParams modificaDocumenti) {
        //TODO CHECK PARAMS
    }
}
