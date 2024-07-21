package com.filemanagement.service.impl;

import com.filemanagement.service.DocumentiService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DocumentiServiceImpl implements DocumentiService {
    @Override
    public String uploadFile(MultipartFile file, String uploadDir) {
        if (file.isEmpty()) {
            return "Per favore, selezionare un file da caricare.";
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadDir + File.separator + file.getOriginalFilename());
            Files.write(path, bytes);

            return "Hai caricato correttamente il file '" + file.getOriginalFilename() + "'";

        } catch (IOException e) {
            e.printStackTrace();
            return "Caricamento del file '" + file.getOriginalFilename() + "'";
        }
    }

    @Override
    public String deleteFile(String nameFile, String uploadDir) {
        return null;
    }
}
