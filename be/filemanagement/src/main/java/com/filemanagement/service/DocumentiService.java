package com.filemanagement.service;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentiService {
    public String uploadFile(MultipartFile file, String uploadDir);

    public String deleteFile(String nameFile, String uploadDir);
}
