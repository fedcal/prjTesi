package com.filemanagement.service.impl;

import com.filemanagement.dto.params.documenti.EliminaDocumento;
import com.filemanagement.dto.params.documenti.ModificaDocumentiParams;
import com.filemanagement.entity.Documenti;
import com.filemanagement.esito.EsitoMessaggiRequestContextHolder;
import com.filemanagement.esito.constants.EsitoOperazioneEnum;
import com.filemanagement.exception.EsitoRuntimeException;
import com.filemanagement.repository.DocumentiRepository;
import com.filemanagement.service.DocumentiService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
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
    @Transactional
    public String rinominaFile(ModificaDocumentiParams modificaDocumenti) {
        esitoMessaggiRequestContextHolder.setOperationId("rinominaFile");

        checkParams(modificaDocumenti);
        Documenti documentoDb = checkEsistenzaFile(modificaDocumenti.getNomeFile(), modificaDocumenti.getPathFile());

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
        try{
            documentiRepository.rinominaFile(modificaDocumenti.getNuovoNome(),modificaDocumenti.getNomeFile(),modificaDocumenti.getPathFile());
        }catch (Exception e){
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Il file non è stato rinominato nel db.");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
        return "Documento rinominato";
    }

    private Documenti checkEsistenzaFile(String nomeFile, String path) {
        Optional<Documenti> documentoFind = documentiRepository.findDocumentiByNomeDocumentoAndPath(nomeFile, path);
        if(!documentoFind.isPresent()){
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Non è stato possibile recuperare il file dal db.");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        File originalFile = null;
        String osName = System.getProperty("os.name");
        if(osName.contains("Wind") || osName.contains("wind")){
            originalFile = new File(path+"\\"+nomeFile+"."+documentoFind.get().getEstensioneDocumento());
        }else{
            originalFile = new File(path+"/"+nomeFile+"."+documentoFind.get().getEstensioneDocumento());
        }

        if(!originalFile.exists()) {
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Non è stato possibile recuperare il file indicato dal file system.");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        return documentoFind.get();
    }

    private void checkParams(ModificaDocumentiParams modificaDocumenti) {
        boolean pathFile = StringUtils.isBlank(modificaDocumenti.getPathFile());
        boolean nomeFile = StringUtils.isBlank(modificaDocumenti.getNomeFile());
        boolean nuovoNomeFile = StringUtils.isBlank(modificaDocumenti.getNuovoNome());

        if((pathFile || nomeFile || nuovoNomeFile) || (pathFile && nomeFile && nuovoNomeFile)){
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Inserire il path del file da modificare, il suo nome e il nuovo nome.");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public String spostaFile(ModificaDocumentiParams modificaDocumenti) {
        //TODO CHECK PARAMS
        checkSpstaFileParams(modificaDocumenti);

        //TODO VERIFICARE ESISTENZA FILE
        Documenti documentoDb = checkEsistenzaFile(modificaDocumenti.getNomeFile(), modificaDocumenti.getPathFile());

        //TODO SPOSTARE IL FILE DAL FILE SYSTEM
        File originalFile = null;
        File moveFile = null;
        String osName = System.getProperty("os.name");

        if(osName.contains("Wind") || osName.contains("wind")){
            originalFile = new File(modificaDocumenti.getPathFile()+"\\"+modificaDocumenti.getNomeFile()+"."+documentoDb.getEstensioneDocumento());
            moveFile = new File(modificaDocumenti.getNuovoPath()+"\\"+modificaDocumenti.getNomeFile()+"."+documentoDb.getEstensioneDocumento());
        }else{
            originalFile = new File(modificaDocumenti.getPathFile()+"/"+modificaDocumenti.getNomeFile()+"."+documentoDb.getEstensioneDocumento());
            moveFile = new File(modificaDocumenti.getNuovoPath()+"/"+modificaDocumenti.getNomeFile()+"."+documentoDb.getEstensioneDocumento());
        }
        try {
            FileUtils.moveFile(FileUtils.getFile(originalFile), FileUtils.getFile(moveFile));
        } catch (IOException e) {
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Non è stato possibile spostare il file.");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        try{
            documentiRepository.updatePathDoc(modificaDocumenti.getNomeFile(),modificaDocumenti.getPathFile(),modificaDocumenti.getNuovoPath());
        }catch(Exception e){
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Non è stato possibile aggiornare il path del documento nel db.");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
        return "Documento spostato";
    }

    private void checkSpstaFileParams(ModificaDocumentiParams modificaDocumenti) {
        boolean pathFile = StringUtils.isBlank(modificaDocumenti.getPathFile());
        boolean nomeFile = StringUtils.isBlank(modificaDocumenti.getNomeFile());
        boolean nuovoNomeFile = StringUtils.isBlank(modificaDocumenti.getNuovoNome());
        boolean nuovoPath = StringUtils.isBlank(modificaDocumenti.getNuovoPath());

        if((pathFile || nomeFile || nuovoNomeFile || nuovoPath) || (pathFile && nomeFile && nuovoNomeFile && nuovoPath)){
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Inserire il path del file da modificare, il suo nome, il nuovo nome e il nuovo path.");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public String eliminaFile(EliminaDocumento eliminaDocumento) {
        checkDeleteFileParams(eliminaDocumento);

        Documenti documentoDb =  checkEsistenzaFile(eliminaDocumento.getNomeFile(), eliminaDocumento.getPathFile());

        File originalFile = null;
        String osName = System.getProperty("os.name");
        if(osName.contains("Wind") || osName.contains("wind")){
            originalFile = new File(eliminaDocumento.getPathFile()+"\\"+eliminaDocumento.getNomeFile()+"."+documentoDb.getEstensioneDocumento());
        }else{
            originalFile = new File(eliminaDocumento.getPathFile()+"/"+eliminaDocumento.getNomeFile()+"."+documentoDb.getEstensioneDocumento());
        }

        boolean deleteFile = originalFile.delete();

        if(!deleteFile){
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Il file non è stato eliminato correttamente.");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        try{
            documentiRepository.deleteById(documentoDb.getIdDocumento());
        }catch(Exception e){
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Il file non è stato eliminato dal db.");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
        return "Documento eliminato";
    }

    private void checkDeleteFileParams(EliminaDocumento eliminaDocumento) {
        boolean pathFile = StringUtils.isBlank(eliminaDocumento.getPathFile());
        boolean nomeFile = StringUtils.isBlank(eliminaDocumento.getNomeFile());

        if((pathFile || nomeFile) || (pathFile && nomeFile)){
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("Inserire il path del file da eliminare e il suo nome.");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }
    }

}
