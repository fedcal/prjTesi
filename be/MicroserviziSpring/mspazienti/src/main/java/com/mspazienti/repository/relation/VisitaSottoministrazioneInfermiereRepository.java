package com.mspazienti.repository.relation;

import com.mspazienti.entity.relation.VisitaSottoministrazioneInfermiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitaSottoministrazioneInfermiereRepository extends JpaRepository<VisitaSottoministrazioneInfermiere,Integer> {
}
