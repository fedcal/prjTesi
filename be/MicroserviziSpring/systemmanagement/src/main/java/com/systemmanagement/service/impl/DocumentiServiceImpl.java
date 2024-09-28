package com.systemmanagement.service.impl;

import com.itextpdf.text.pdf.PdfReader;
import com.systemmanagement.dto.CustomMultipartFile;
import com.systemmanagement.dto.DocumentiDto;
import com.systemmanagement.dto.params.documenti.EliminaDocumento;
import com.systemmanagement.dto.params.documenti.FindDocumentoParams;
import com.systemmanagement.dto.params.documenti.ModificaDocumentiParams;
import com.systemmanagement.dto.response.LoadFileResponse;
import com.systemmanagement.entity.Cartelle;
import com.systemmanagement.entity.Documenti;
import com.systemmanagement.entity.RagBotPdf;
import com.systemmanagement.esito.EsitoMessaggiRequestContextHolder;
import com.systemmanagement.esito.Messaggio;
import com.systemmanagement.esito.constants.EsitoOperazioneEnum;
import com.systemmanagement.esito.constants.SeveritaMessaggioEnum;
import com.systemmanagement.exception.EsitoRuntimeException;
import com.systemmanagement.mapper.documenti.DocumentiDtoMapper;
import com.systemmanagement.repository.CartelleRepository;
import com.systemmanagement.repository.DocumentiRepository;
import com.systemmanagement.repository.RagBotPdfRepository;
import com.systemmanagement.service.DocumentiService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class DocumentiServiceImpl implements DocumentiService {

    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;
    private final DocumentiRepository documentiRepository;
    private final CartelleRepository cartelleRepository;
    private final RagBotPdfRepository ragBotPdfRepository;

    @Override
    public String uploadFile(MultipartFile file, String uploadDir) {
        esitoMessaggiRequestContextHolder.setOperationId("uploadFile");
        if (file.isEmpty()) {
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è stato possibile caricare il nuovo documento, cartella non trovata").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadDir + File.separator + file.getOriginalFilename());
            Files.write(path, bytes);


            String nomeCartella = null;
            String osName = System.getProperty("os.name");
            String[] splitPath = null;
            if(osName.contains("Wind") || osName.contains("wind")){
                splitPath = uploadDir.split("\\\\");
            } else {
                splitPath = uploadDir.split("/");
            }

            nomeCartella = splitPath[splitPath.length-1];

            Optional<Cartelle> cartellaFind = cartelleRepository.findByNomeCartella(nomeCartella);

            if(cartellaFind.isEmpty()){
                Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è stato possibile caricare il nuovo documento, cartella non trovata").build();
                esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
                esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
                throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
            }

            String[] fileNameSplit = file.getOriginalFilename().split("\\.");
            //Salvare file su db
            Documenti documentoToSave = new Documenti();
            documentoToSave.setNomeDocumento(file.getOriginalFilename());
            documentoToSave.setEstensioneDocumento(fileNameSplit[fileNameSplit.length-1]);
            documentoToSave.setPath(uploadDir.replace("\\\\","\\"));
            documentoToSave.setCartella(cartellaFind.get());
            documentiRepository.save(documentoToSave);

            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
            return "Hai caricato correttamente il file '" + file.getOriginalFilename() + "'";

        } catch (IOException e) {
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Caricamento del file non riuscito correttamente.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
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
            originalFile = new File(modificaDocumenti.getPathFile()+"\\"+modificaDocumenti.getNomeFile());
            renameFile = new File(modificaDocumenti.getPathFile()+"\\"+modificaDocumenti.getNuovoNome()+"."+documentoDb.getEstensioneDocumento());
        }else{
            originalFile = new File(modificaDocumenti.getPathFile()+"/"+modificaDocumenti.getNomeFile());
            renameFile = new File(modificaDocumenti.getPathFile()+"/"+modificaDocumenti.getNuovoNome()+"."+documentoDb.getEstensioneDocumento());
        }

        boolean renameChek = originalFile.renameTo(renameFile);
        if(!renameChek){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Il file non è stato rinominato correttamente.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }
        try{
            documentiRepository.rinominaFile(modificaDocumenti.getNuovoNome(),modificaDocumenti.getNomeFile(),modificaDocumenti.getPathFile());
        }catch (Exception e){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Il file non è stato rinominato nel db.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
        return "Documento rinominato";
    }

    private Documenti checkEsistenzaFile(String nomeFile, String path) {
        Optional<Documenti> documentoFind = documentiRepository.findDocumentiByNomeDocumentoAndPath(nomeFile, path);
        if(!documentoFind.isPresent()){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è stato possibile recuperare il file dal db.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        File originalFile = null;
        String osName = System.getProperty("os.name");
        if(osName.contains("Wind") || osName.contains("wind")){
            originalFile = new File(path+"\\"+nomeFile);
        }else{
            originalFile = new File(path+"/"+nomeFile);
        }

        if(!originalFile.exists()) {
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è stato possibile recuperare il file indicato dal file system.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        return documentoFind.get();
    }

    private void checkParams(ModificaDocumentiParams modificaDocumenti) {
        boolean pathFile = StringUtils.isBlank(modificaDocumenti.getPathFile());
        boolean nomeFile = StringUtils.isBlank(modificaDocumenti.getNomeFile());
        boolean nuovoNomeFile = StringUtils.isBlank(modificaDocumenti.getNuovoNome());

        if((pathFile || nomeFile || nuovoNomeFile) || (pathFile && nomeFile && nuovoNomeFile)){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Inserire il path del file da modificare, il suo nome e il nuovo nome.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public String spostaFile(ModificaDocumentiParams modificaDocumenti) {
        esitoMessaggiRequestContextHolder.setOperationId("spostaFile");
        checkSpstaFileParams(modificaDocumenti);

        Documenti documentoDb = checkEsistenzaFile(modificaDocumenti.getNomeFile(), modificaDocumenti.getPathFile());

        File originalFile = null;
        File moveFile = null;
        String osName = System.getProperty("os.name");

        if(osName.contains("Wind") || osName.contains("wind")){
            originalFile = new File(modificaDocumenti.getPathFile()+"\\"+modificaDocumenti.getNomeFile());
            moveFile = new File(modificaDocumenti.getNuovoPath()+"\\"+modificaDocumenti.getNomeFile());
        }else{
            originalFile = new File(modificaDocumenti.getPathFile()+"/"+modificaDocumenti.getNomeFile());
            moveFile = new File(modificaDocumenti.getNuovoPath()+"/"+modificaDocumenti.getNomeFile());
        }
        try {
            FileUtils.moveFile(FileUtils.getFile(originalFile), FileUtils.getFile(moveFile));
        } catch (IOException e) {
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è stato possibile spostare il file.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        try{
            documentiRepository.updatePathDoc(modificaDocumenti.getNomeFile(),modificaDocumenti.getPathFile(),modificaDocumenti.getNuovoPath());
        }catch(Exception e){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è stato possibile aggiornare il path del documento nel db.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
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
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Inserire il path del file da modificare, il suo nome, il nuovo nome e il nuovo path.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public String eliminaFile(EliminaDocumento eliminaDocumento) {
        esitoMessaggiRequestContextHolder.setOperationId("eliminaFile");
        checkDeleteFileParams(eliminaDocumento);

        Documenti documentoDb =  checkEsistenzaFile(eliminaDocumento.getNomeFile(), eliminaDocumento.getPathFile());

        File originalFile = null;
        String osName = System.getProperty("os.name");
        if(osName.contains("Wind") || osName.contains("wind")){
            originalFile = new File(eliminaDocumento.getPathFile()+"\\"+eliminaDocumento.getNomeFile());
        }else{
            originalFile = new File(eliminaDocumento.getPathFile()+"/"+eliminaDocumento.getNomeFile());
        }

        boolean deleteFile = originalFile.delete();

        if(!deleteFile){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Il file non è stato eliminato correttamente.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        try{
            documentiRepository.deleteById(documentoDb.getIdDocumento());
        }catch(Exception e){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Il file non è stato eliminato dal db.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
        return "Documento eliminato";
    }

    @Override
    public List<DocumentiDto> elencoFile() {
        esitoMessaggiRequestContextHolder.setOperationId("elencoFile");
        List<Documenti> documentiList = documentiRepository.findAll();

        List<DocumentiDto> documentiDto =  null;

        if(!documentiList.isEmpty()){
            documentiDto = DocumentiDtoMapper.INSTANCE.toDtos(documentiList);
        }else{
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è presente nessun documento.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
        return documentiDto;
    }

    private void checkDeleteFileParams(EliminaDocumento eliminaDocumento) {
        boolean pathFile = StringUtils.isBlank(eliminaDocumento.getPathFile());
        boolean nomeFile = StringUtils.isBlank(eliminaDocumento.getNomeFile());

        if((pathFile || nomeFile) || (pathFile && nomeFile)){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è stato possibile caricare il nuovo documento, cartella non trovata").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public DocumentiDto findFile(FindDocumentoParams findDocumentoParams) {
        DocumentiDto findDocumentoDto = null;
        Optional<Documenti> documentiOptional = Optional.empty();
        esitoMessaggiRequestContextHolder.setOperationId("findFile");

        if(!StringUtils.isBlank(findDocumentoParams.getTitoloDocumento())
            && !StringUtils.isBlank(findDocumentoParams.getPathDocumento())){
            documentiOptional = documentiRepository.findDocumentiByNomeDocumentoAndPath(findDocumentoParams.getTitoloDocumento(),findDocumentoParams.getPathDocumento());
        }else if(!StringUtils.isBlank(findDocumentoParams.getTitoloDocumento())){
            documentiOptional = documentiRepository.findDocumentiByNomeDocumento(findDocumentoParams.getTitoloDocumento());
        }

        if(documentiOptional.isEmpty()){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è presente nessun documento.").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            esitoMessaggiRequestContextHolder.setOperationId("findFile");
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }else{
            findDocumentoDto = DocumentiDtoMapper.INSTANCE.toDto(documentiOptional.get());
        }
        esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
        return findDocumentoDto;
    }

    @Override
    public String addestramentoSingolo(MultipartFile file, String nomeBot) {
        esitoMessaggiRequestContextHolder.setOperationId("addestramentoSingolo");

        if (file.isEmpty()) {
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Non è stato possibile caricare il nuovo documento, cartella non trovata").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        Optional<RagBotPdf> ragBotPdf = ragBotPdfRepository.findByNomeBot(nomeBot);

        if(ragBotPdf.isEmpty()){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Bot non registrato").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        try {
            MultipartBodyBuilder builder = new MultipartBodyBuilder();
            builder.part("file", new InputStreamResource(file.getInputStream()))
                    .header("Content-Disposition", "form-data; name=file; filename=" + file.getOriginalFilename());

            MultiValueMap<String, HttpEntity<?>> multipartRequest = builder.build();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<MultiValueMap<String, HttpEntity<?>>> requestEntity = new HttpEntity<>(multipartRequest, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<LoadFileResponse> response;
            response = restTemplate.exchange(ragBotPdf.get().getUrlPython()+"load-pdf", HttpMethod.POST, requestEntity, LoadFileResponse.class);
        } catch (RestClientException e) {
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Connessione non riuscita").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Salvare file su db
        String[] fileNameSplit = file.getOriginalFilename().split("\\.");
        Documenti documentoToSave = new Documenti();
        documentoToSave.setNomeDocumento(file.getOriginalFilename());
        documentoToSave.setEstensioneDocumento(fileNameSplit[fileNameSplit.length-1]);
        documentoToSave.setPath(ragBotPdf.get().getCartellaAddestramento().getPath());
        documentoToSave.setCartella(ragBotPdf.get().getCartellaAddestramento());
        documentiRepository.save(documentoToSave);

        esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
        return "Addestramento del singolo file nel bot "+nomeBot+" completato";
    }

    @Override
    public String addestramentoMassivo(String nomeBot) {
        esitoMessaggiRequestContextHolder.setOperationId("addestramentoMassivo");

        Optional<RagBotPdf> ragBotPdf = ragBotPdfRepository.findByNomeBot(nomeBot);

        if(ragBotPdf.isEmpty()){
            Messaggio messaggio = Messaggio.builder().severita(SeveritaMessaggioEnum.INFO).codMsg("Bot non registrato").build();
            esitoMessaggiRequestContextHolder.getMessaggi().add(messaggio);
            esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.KO);
            throw new EsitoRuntimeException(HttpStatus.BAD_REQUEST);
        }

        File folder = null;
        String osName = System.getProperty("os.name");
        if(osName.contains("Wind") || osName.contains("wind")){
            folder = new File((ragBotPdf.get().getCartellaCaricamentoMassivo().getPath() + "\\" + ragBotPdf.get().getCartellaCaricamentoMassivo().getNomeCartella()));
        }else{
            folder = new File((ragBotPdf.get().getCartellaCaricamentoMassivo().getPath() + "/" + ragBotPdf.get().getCartellaCaricamentoMassivo().getNomeCartella()));
        }

        File[] files = folder.listFiles();

        if(files!=null && files.length>0){
            for(File file : files) {
                if (file.getName().contains(" ")) {

                    if (osName.contains("Wind") || osName.contains("wind")) {
                        file.renameTo(new File(folder.getAbsolutePath() + "\\" + file.getName().replace(" ", "_")));
                    } else {
                        file.renameTo(new File(folder.getAbsolutePath() + "/" + file.getName().replace(" ", "_")));
                    }
                }
            }
        }

        files = folder.listFiles();

        if(files!=null && files.length>0){
            List<ResponseEntity<LoadFileResponse>> responsesFileUpload = new ArrayList<>();
            for(File file : files){
                if (!file.exists() || file.length() == 0) {
                    System.out.println("Il file non esiste o è vuoto.");
                }

                if (!file.getName().toLowerCase().endsWith(".pdf")) {
                    System.out.println("Il file non ha estensione .pdf.");
                }

                try (FileInputStream fis = new FileInputStream(file)) {
                    PdfReader reader = new PdfReader(fis);
                    if (reader.getNumberOfPages() == 0) {
                        System.out.println("Il PDF non contiene pagine.");
                    }
                } catch (IOException e) {
                    System.out.println("Errore durante la lettura del PDF: " + e.getMessage());
                }
            }
            for(File file : files){

                try {
                    MultipartFile multipartFile = new CustomMultipartFile(file);

                    MultipartBodyBuilder builder = new MultipartBodyBuilder();
                    builder.part("file", new ByteArrayResource(multipartFile.getBytes()))
                            .header("Content-Disposition", "form-data; name=file; filename=" + multipartFile.getOriginalFilename());

                    MultiValueMap<String, HttpEntity<?>> multipartRequest = builder.build();

                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.MULTIPART_FORM_DATA);

                    HttpEntity<MultiValueMap<String, HttpEntity<?>>> requestEntity = new HttpEntity<>(multipartRequest, headers);

                    RestTemplate restTemplate = new RestTemplate();
                    ResponseEntity<LoadFileResponse> response;
                    response = restTemplate.exchange(ragBotPdf.get().getUrlPython()+"load-pdf", HttpMethod.POST, requestEntity, LoadFileResponse.class);

                    if(response.getBody().getStatus().equals("failed")){
                        
                    }

                    //Salvare file su db
                    String[] fileNameSplit = multipartFile.getOriginalFilename().split("\\.");
                    Documenti documentoToSave = new Documenti();
                    documentoToSave.setNomeDocumento(file.getName());
                    documentoToSave.setEstensioneDocumento(fileNameSplit[fileNameSplit.length-1]);
                    documentoToSave.setPath(ragBotPdf.get().getCartellaAddestramento().getPath());
                    documentoToSave.setCartella(ragBotPdf.get().getCartellaAddestramento());
                    documentiRepository.save(documentoToSave);

                    file.delete();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }

        esitoMessaggiRequestContextHolder.setCodRet(EsitoOperazioneEnum.OK);
        return "Addestramento massivo del bot "+nomeBot+" completato";
    }

}
