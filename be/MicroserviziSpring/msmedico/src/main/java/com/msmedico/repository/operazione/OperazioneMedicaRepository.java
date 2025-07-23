package com.msmedico.repository.operazione;

import com.msmedico.entity.operazione.OperazioneMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperazioneMedicaRepository extends JpaRepository<OperazioneMedica, Integer> {
}
