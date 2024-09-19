package com.bff.service;

import com.bff.dto.RagBotPdfDto;
import com.bff.dto.params.ragbotpdf.ModificaBotParams;
import com.bff.dto.params.ragbotpdf.RegistraBotParams;

import java.util.List;

public interface RagBotManagementService {
    List<RagBotPdfDto> elencoBot();

    RagBotPdfDto registraBot(RegistraBotParams registraBotParams);

    RagBotPdfDto modificaBot(ModificaBotParams modificaBotParams);

    String eliminaBot(String nomeBot);
}
