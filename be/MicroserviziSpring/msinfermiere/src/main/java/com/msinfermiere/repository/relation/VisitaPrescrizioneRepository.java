package com.msinfermiere.repository.relation;

import com.msinfermiere.entity.relation.VisitaPrescrizione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitaPrescrizioneRepository extends JpaRepository<VisitaPrescrizione, Integer> {
}
