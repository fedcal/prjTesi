package com.filemanagement.repository;

import com.filemanagement.entity.Cartelle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartelleRepository extends JpaRepository<Cartelle,Long> {
    Optional<Cartelle> findByNomeCartella(String nameFolder);
}