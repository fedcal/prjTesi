package com.systemmanagement.repository;

import com.systemmanagement.entity.RagBotPdf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RagBotPdfRepository extends JpaRepository<RagBotPdf,Integer> {
    @Modifying
    @Query("DELETE FROM RagBotPdf bot WHERE bot.nomeBot = :nomeBot")
    void deleteBotRagByName(@Param("nomeBot") String nomeBot);

    Optional<RagBotPdf> findByNomeBot(String nomeBot);
}
