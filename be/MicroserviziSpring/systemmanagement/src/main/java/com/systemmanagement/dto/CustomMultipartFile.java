package com.systemmanagement.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class CustomMultipartFile implements MultipartFile {
    private final byte[] fileContent;
    private final String fileName;
    private final String contentType;

    public CustomMultipartFile(File file) throws IOException {
        this.fileName = file.getName();
        this.contentType = "application/pdf";
        this.fileContent = readContent(file);
    }

    private byte[] readContent(File file) throws IOException {
        try (InputStream inputStream = new FileInputStream(file)) {
            return inputStream.readAllBytes();
        }
    }

    @Override
    public String getName() {
        return "file"; // Nome del campo del form
    }

    @Override
    public String getOriginalFilename() {
        return this.fileName;
    }

    @Override
    public String getContentType() {
        return this.contentType;
    }

    @Override
    public boolean isEmpty() {
        return fileContent == null || fileContent.length == 0;
    }

    @Override
    public long getSize() {
        return fileContent.length;
    }

    @Override
    public byte[] getBytes() {
        return this.fileContent;
    }

    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(this.fileContent);
    }

    @Override
    public void transferTo(File dest) throws IOException {
        try (OutputStream outStream = new FileOutputStream(dest)) {
            outStream.write(fileContent);
        }
    }
}
