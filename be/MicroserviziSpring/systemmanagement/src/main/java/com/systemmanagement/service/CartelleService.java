package com.systemmanagement.service;

import com.systemmanagement.dto.CartelleDto;
import com.systemmanagement.dto.params.cartella.CartellaParams;
import com.systemmanagement.dto.params.cartella.ModificaCartellaParams;

import java.util.List;

public interface CartelleService {
    CartelleDto creaCartella(CartellaParams cartellaParams);
    String eliminaCartella(CartellaParams cartellaParams);
    CartelleDto rinominaCartella(ModificaCartellaParams cartellaParams);

    List<CartelleDto> elencoCartelle();

    CartelleDto findCartella(CartellaParams cartellaParams);

    CartelleDto aggiungiCartella(CartellaParams cartellaParams);
}
