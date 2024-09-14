package com.systemmanagement.service;

import com.systemmanagement.dto.RagBotPdfDto;
import com.systemmanagement.dto.params.ragbotpdf.ModificaBotParams;
import com.systemmanagement.dto.params.ragbotpdf.RegistraBotParams;

import java.util.List;

public interface RagBotPdfService {
    List<RagBotPdfDto> elencoBot();

    RagBotPdfDto registraBot(RegistraBotParams registraBotParams);

    RagBotPdfDto modificaBot(ModificaBotParams modificaBotParams);

    String eliminaBot(String nomeBot);
}
