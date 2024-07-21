package com.filemanagement.repository;

import com.filemanagement.entity.Documenti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentiRepository extends JpaRepository<Documenti,Long> {
}
