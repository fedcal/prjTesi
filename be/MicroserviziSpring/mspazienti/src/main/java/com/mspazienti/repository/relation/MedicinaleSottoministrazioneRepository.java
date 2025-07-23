package com.mspazienti.repository.relation;

import com.mspazienti.entity.relation.MedicinaleSottoministrazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicinaleSottoministrazioneRepository extends JpaRepository<MedicinaleSottoministrazione, Integer> {
}
