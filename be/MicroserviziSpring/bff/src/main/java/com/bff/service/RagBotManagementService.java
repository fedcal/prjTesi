package com.bff.service;

import com.bff.dto.RagBotPdfDto;
import com.bff.dto.request.ragbotpdf.ModificaBotParams;
import com.bff.dto.request.ragbotpdf.RegistraBotParams;
import com.bff.dto.request.ragbotpdf.TrovaBotParams;

import java.util.List;

public interface RagBotManagementService {
    List<RagBotPdfDto> elencoBot();

    RagBotPdfDto registraBot(RegistraBotParams registraBotParams);

    RagBotPdfDto modificaBot(ModificaBotParams modificaBotParams);

    String eliminaBot(String nomeBot);

    RagBotPdfDto trovaBot(TrovaBotParams trovaBotParam);
}
