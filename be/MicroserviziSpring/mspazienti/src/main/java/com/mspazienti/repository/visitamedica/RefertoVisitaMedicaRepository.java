package com.mspazienti.repository.visitamedica;

import com.mspazienti.entity.visitamedica.RefertoVisitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefertoVisitaMedicaRepository extends JpaRepository<RefertoVisitaMedica, Integer> {
}
