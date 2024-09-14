package com.bff.controller;

import com.bff.esito.EsitoMessaggiRequestContextHolder;
import com.bff.esito.GenericResponseConverter;
import com.bff.esito.GenericResponseDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;


    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public ResponseEntity<GenericResponseDto<String>> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("uploadDir") String uploadDir) {
        if (file.isEmpty()) {
            return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse("Please select a file to upload."));
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadDir + File.separator + file.getOriginalFilename());
            Files.write(path, bytes);

            return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse("You successfully uploaded '" + file.getOriginalFilename() + "'"));

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.ok(esitoMessaggiRequestContextHolder.buildGenericResponse("Failed to upload '" + file.getOriginalFilename() + "'"));
        }
    }
}
