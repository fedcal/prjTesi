package com.msmedico.repository.relation;

import com.msmedico.entity.relation.OperazionePrescrizione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperazionePrescrizioneRepository extends JpaRepository<OperazionePrescrizione, Integer> {
}