package com.systemmanagement.repository;

import com.systemmanagement.entity.Cartelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartelleRepository extends JpaRepository<Cartelle,Long> {
    Optional<Cartelle> findByNomeCartella(String nomeCartella);

    Optional<Cartelle> findByNomeCartellaAndPath(String nomeCartella, String path);

    @Modifying
    @Query("DELETE FROM Cartelle c WHERE c.nomeCartella = :nomeCartella")
    void deleteCartelleByNomeCartella(@Param("nomeCartella") String nomeCartella);

    @Modifying
    @Query("DELETE FROM Cartelle c WHERE c.nomeCartella = :nomeCartella AND c.path = :path")
    void deleteCartelleByNomeCartellaAndPath(@Param("nomeCartella") String nomeCartella,
                                             @Param("path") String path);
}