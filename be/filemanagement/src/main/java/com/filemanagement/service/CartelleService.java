package com.filemanagement.service;

import com.filemanagement.dto.params.cartella.CartellaParams;
import com.filemanagement.dto.params.cartella.ModificaCartellaParams;

public interface CartelleService {
    String aggiungiCartella(CartellaParams cartellaParams);
    String eliminaCartella(CartellaParams cartellaParams);
    String rinominaCartella(ModificaCartellaParams cartellaParams);
}
