package com.msinfermiere.repository.relation;

import com.msinfermiere.entity.relation.MedicinaleSottoministrazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicinaleSottoministrazioneRepository extends JpaRepository<MedicinaleSottoministrazione, Integer> {
}
