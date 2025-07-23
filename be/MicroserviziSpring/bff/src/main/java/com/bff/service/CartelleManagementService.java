package com.bff.service;

import com.bff.dto.CartelleDto;
import com.bff.dto.request.cartella.CartellaParams;
import com.bff.dto.request.cartella.ModificaCartellaParams;
import jakarta.validation.Valid;

import java.util.List;

public interface CartelleManagementService {
    CartelleDto creaCartella(@Valid CartellaParams cartellaParams);

    CartelleDto aggiungiCartella(@Valid CartellaParams cartellaParams);

    CartelleDto rinominaCartella(@Valid ModificaCartellaParams cartellaParams);

    String eliminaCartella(CartellaParams cartellaParams);

    List<CartelleDto> elencoCartelle();

    CartelleDto findCartella(CartellaParams cartellaParams);
}
