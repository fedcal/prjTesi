package com.msmedico.repository.relation;

import com.msmedico.entity.relation.VisitaSottoministrazioneInfermiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitaSottoministrazioneInfermiereRepository extends JpaRepository<VisitaSottoministrazioneInfermiere,Integer> {
}
