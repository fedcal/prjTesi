package com.msinfermiere.repository.relation;

import com.msinfermiere.entity.relation.VisitaSottoministrazioneMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitaSottoministrazioneMedicoRepository extends JpaRepository<VisitaSottoministrazioneMedico, Integer> {
}
