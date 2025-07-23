package com.mspazienti.repository;

import com.mspazienti.entity.Diagnosi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosiRepository extends JpaRepository<Diagnosi, Integer> {
}
