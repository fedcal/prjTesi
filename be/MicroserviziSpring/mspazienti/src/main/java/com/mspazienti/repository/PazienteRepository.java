package com.mspazienti.repository;

import com.mspazienti.entity.Paziente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PazienteRepository extends JpaRepository<Paziente, Integer> {
}
