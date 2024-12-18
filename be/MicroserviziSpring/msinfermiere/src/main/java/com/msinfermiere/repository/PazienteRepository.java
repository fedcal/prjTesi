package com.msinfermiere.repository;

import com.msinfermiere.entity.Paziente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PazienteRepository extends JpaRepository<Paziente, Long> {
}
