package com.bff.service.impl;

import com.bff.dto.CartelleDto;
import com.bff.dto.params.cartella.CartellaParams;
import com.bff.dto.params.cartella.ModificaCartellaParams;
import com.bff.esito.EsitoMessaggiRequestContextHolder;
import com.bff.esito.GenericResponseConverter;
import com.bff.esito.GenericResponseDto;
import com.bff.filemanagement.api.CartelleControllerApi;
import com.bff.service.CartelleManagementService;
import com.bff.utils.ConverterObjectParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartelleManagementServiceImpl implements CartelleManagementService {

    private final EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder;

    private final GenericResponseConverter genericResponseConverter;

    private final CartelleControllerApi cartelleControllerApi;

    @Override
    public CartelleDto creaCartella(CartellaParams cartellaParams) {
        GenericResponseDto<CartelleDto> creaCartellaDto = genericResponseConverter.convertGenericResponse(
                cartelleControllerApi.creaCartella(ConverterObjectParameter.convertCartellaParams(cartellaParams)), CartelleDto.class);
        return creaCartellaDto.getPayload();
    }

    @Override
    public CartelleDto aggiungiCartella(CartellaParams cartellaParams) {
        GenericResponseDto<CartelleDto> aggiungiCartellaDto = genericResponseConverter.convertGenericResponse(
                cartelleControllerApi.aggiungiCartella(ConverterObjectParameter.convertCartellaParams(cartellaParams)), CartelleDto.class);
        return aggiungiCartellaDto.getPayload();
    }

    @Override
    public CartelleDto rinominaCartella(ModificaCartellaParams cartellaParams) {
        GenericResponseDto<CartelleDto> modificaCartellaDto = genericResponseConverter.convertGenericResponse(
                cartelleControllerApi.aggiungiCartella(ConverterObjectParameter.convertModificaCartellaParams(cartellaParams)), CartelleDto.class);
        return modificaCartellaDto.getPayload();
    }

    @Override
    public String eliminaCartella(CartellaParams cartellaParams) {
        GenericResponseDto<String> eliminaCartellaResponse = genericResponseConverter.convertGenericResponse(
                cartelleControllerApi.aggiungiCartella(ConverterObjectParameter.convertCartellaParams(cartellaParams)), String.class);
        return eliminaCartellaResponse.getPayload();
    }

    @Override
    public List<CartelleDto> elencoCartelle() {
        GenericResponseDto<List<CartelleDto>> elencoCartelleList = genericResponseConverter.convertGenericResponseList(
                cartelleControllerApi.elencoCartelle(), CartelleDto.class);
        return elencoCartelleList.getPayload();
    }

    @Override
    public CartelleDto findCartella(CartellaParams cartellaParams) {
        GenericResponseDto<CartelleDto> findCartellaDtoResponse = genericResponseConverter.convertGenericResponse(
                cartelleControllerApi.findCartella(ConverterObjectParameter.convertCartellaParams(cartellaParams)), CartelleDto.class);
        return findCartellaDtoResponse.getPayload();
    }
}
