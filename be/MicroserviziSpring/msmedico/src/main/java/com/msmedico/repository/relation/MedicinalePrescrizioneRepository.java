package com.msmedico.repository.relation;

import com.msmedico.entity.relation.MedicinalePrescrizione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicinalePrescrizioneRepository extends JpaRepository<MedicinalePrescrizione, Integer> {
}
