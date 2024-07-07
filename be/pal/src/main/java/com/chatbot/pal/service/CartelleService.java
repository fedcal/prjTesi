package com.chatbot.pal.service;

public interface CartelleService {
    public String aggiungiCartella(String nameFolder, String path);
    public String eliminaCartella(String nameFolder);
    public String renameFolder(String oldFolderName, String newFolderName);
}
