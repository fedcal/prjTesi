package com.mspazienti.repository.relation;

import com.mspazienti.entity.relation.VisitaPrescrizione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitaPrescrizioneRepository extends JpaRepository<VisitaPrescrizione, Integer> {
}
