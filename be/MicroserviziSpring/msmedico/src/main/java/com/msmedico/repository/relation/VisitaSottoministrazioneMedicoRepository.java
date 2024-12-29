package com.msmedico.repository.relation;

import com.msmedico.entity.relation.VisitaSottoministrazioneMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitaSottoministrazioneMedicoRepository extends JpaRepository<VisitaSottoministrazioneMedico, Integer> {
}
