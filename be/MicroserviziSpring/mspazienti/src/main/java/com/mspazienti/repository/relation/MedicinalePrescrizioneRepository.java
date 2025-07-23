package com.mspazienti.repository.relation;

import com.mspazienti.entity.relation.MedicinalePrescrizione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicinalePrescrizioneRepository extends JpaRepository<MedicinalePrescrizione, Integer> {
}
