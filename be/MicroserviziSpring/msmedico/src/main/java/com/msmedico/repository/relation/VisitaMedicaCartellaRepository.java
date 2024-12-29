package com.msmedico.repository.relation;

import com.msmedico.entity.relation.VisitaMedicaCartella;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitaMedicaCartellaRepository extends JpaRepository<VisitaMedicaCartella, Integer> {
}
