package com.mspazienti.repository.relation;

import com.mspazienti.entity.relation.OperazionePrescrizione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperazionePrescrizioneRepository extends JpaRepository<OperazionePrescrizione, Integer> {
}
