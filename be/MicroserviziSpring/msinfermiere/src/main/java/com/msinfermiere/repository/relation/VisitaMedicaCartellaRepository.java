package com.msinfermiere.repository.relation;

import com.msinfermiere.entity.relation.VisitaMedicaCartella;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitaMedicaCartellaRepository extends JpaRepository<VisitaMedicaCartella, Integer> {
}
