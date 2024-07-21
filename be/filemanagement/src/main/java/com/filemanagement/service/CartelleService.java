package com.filemanagement.service;

import com.filemanagement.dto.params.CartellaParams;
import com.filemanagement.dto.params.ModificaCartellaParams;

public interface CartelleService {
    public String aggiungiCartella(CartellaParams cartellaParams);
    public String eliminaCartella(CartellaParams cartellaParams);
    public String rinominaCartella(ModificaCartellaParams cartellaParams);
}
