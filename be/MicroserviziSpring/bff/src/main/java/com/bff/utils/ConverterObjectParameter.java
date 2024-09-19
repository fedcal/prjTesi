package com.bff.utils;

import com.bff.dto.params.cartella.CartellaParams;
import com.bff.dto.params.cartella.ModificaCartellaParams;
import com.bff.dto.params.ragbotpdf.RegistraBotParams;
import com.bff.dto.params.ragbotpdf.ModificaBotParams;


public final class ConverterObjectParameter {
    private ConverterObjectParameter() {}

    public static com.bff.filemanagement.model.CartellaParams convertCartellaParams(CartellaParams cartellaParams){
        com.bff.filemanagement.model.CartellaParams cartellaParamsConvert = new com.bff.filemanagement.model.CartellaParams();

        cartellaParamsConvert.setNomeCartella(cartellaParams.getNomeCartella());
        cartellaParamsConvert.setPath(cartellaParams.getPath());
        cartellaParamsConvert.setIsCartellaAddestramento(cartellaParams.getIsCartellaAddestramento());

        return cartellaParamsConvert;
    }

    public static com.bff.filemanagement.model.CartellaParams convertModificaCartellaParams(ModificaCartellaParams cartellaParams) {
        com.bff.filemanagement.model.CartellaParams cartellaParamsConvert = new com.bff.filemanagement.model.CartellaParams();

        cartellaParamsConvert.setNomeCartella(cartellaParams.getNomeCartella());
        cartellaParamsConvert.setPath(cartellaParams.getPath());
        cartellaParamsConvert.setIsCartellaAddestramento(cartellaParams.getIsCartellaAddestramento());

        return cartellaParamsConvert;
    }

    public static com.bff.filemanagement.model.RegistraBotParams convertRegistraBot(RegistraBotParams registraBotParams) {
        com.bff.filemanagement.model.RegistraBotParams registraBotParamsConvert =new com.bff.filemanagement.model.RegistraBotParams();

        registraBotParamsConvert.setNomeBot(registraBotParams.getNomeBot());
        registraBotParamsConvert.setDescrizioneBot(registraBotParams.getDescrizioneBot());
        registraBotParamsConvert.setUrlSpring(registraBotParams.getUrlSpring());
        registraBotParamsConvert.setUrlPython(registraBotParams.getUrlPython());
        registraBotParamsConvert.setUrlSpring(registraBotParams.getUrlSpring());
        registraBotParamsConvert.setNomeCartellaAddestramento(registraBotParams.getNomeCartellaAddestramento());
        registraBotParamsConvert.setNomeCartellaCaricamentoMassivo(registraBotParams.getNomeCartellaCaricamentoMassivo());

        return registraBotParamsConvert;
    }

    public static com.bff.filemanagement.model.ModificaBotParams convertModificaBot(ModificaBotParams modificaBotParams) {
        com.bff.filemanagement.model.ModificaBotParams modificaBotParamsConvert = new com.bff.filemanagement.model.ModificaBotParams();

        modificaBotParamsConvert.setNomeBot(modificaBotParams.getNomeBot());
        modificaBotParamsConvert.setNuovaDescrizioneBot(modificaBotParams.getNuovaDescrizioneBot());
        modificaBotParamsConvert.setNuovoNomeBot(modificaBotParams.getNuovoNomeBot());
        modificaBotParamsConvert.setNuovoUrlPython(modificaBotParams.getNuovoUrlPython());
        modificaBotParamsConvert.setNuovoUrlSpring(modificaBotParams.getNuovoUrlSpring());
        modificaBotParamsConvert.setNuovoNomeCartellaAddestramento(modificaBotParams.getNuovoNomeCartellaAddestramento());
        modificaBotParamsConvert.setNuovoNomeCartellaCaricamentoMassivo(modificaBotParams.getNuovoNomeCartellaCaricamentoMassivo());

        return modificaBotParamsConvert;
    }
}