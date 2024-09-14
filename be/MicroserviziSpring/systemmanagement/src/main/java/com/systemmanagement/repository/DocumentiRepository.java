package com.systemmanagement.repository;

import com.systemmanagement.entity.Documenti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DocumentiRepository extends JpaRepository<Documenti,Long> {
    Optional<Documenti> findDocumentiByNomeDocumentoAndPath(String nomeDocumento,
                                                            String pathDocumento);

    Optional<Documenti> findDocumentiByNomeDocumento(String nomeDocumento);

    @Modifying
    @Query(value = "UPDATE documenti dc SET dc.nome_documento = :nuovoNome WHERE dc.nome_documento = :nomeFile AND dc.path = :pathFile",nativeQuery = true)
    void rinominaFile(@Param("nuovoNome") String nuovoNome,
                      @Param("nomeFile") String nomeFile,
                      @Param("pathFile") String pathFile);

    @Modifying
    @Query(value = "UPDATE documenti dc SET dc.path = :nuovoPath WHERE dc.nome_documento = :nomeFile AND dc.path = :pathFile",nativeQuery = true)
    void updatePathDoc(@Param("nomeFile") String nomeFile,
                       @Param("pathFile") String pathFile,
                       @Param("nuovoPath") String nuovoPath);
}
