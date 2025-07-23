package com.msmedico.repository.visitamedica;

import com.msmedico.entity.visitamedica.VisitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitaMedicaRepository extends JpaRepository<VisitaMedica, Integer> {
}
