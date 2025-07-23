package com.mspazienti.repository;

import com.mspazienti.entity.Reparto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepartoRepository extends JpaRepository<Reparto, Integer> {
}

