package com.filemanagement.repository;

import com.filemanagement.entity.Cartelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartelleRepository extends JpaRepository<Cartelle,Long> {
    Optional<Cartelle> findByNomeCartella(String nameFolder);

    @Modifying
    @Query("DELETE FROM Cartelle c WHERE c.nomeCartella = :nomeCartella")
    void deleteCartelleByNomeCartella(@Param("nomeCartella") String nomeCartella);
}