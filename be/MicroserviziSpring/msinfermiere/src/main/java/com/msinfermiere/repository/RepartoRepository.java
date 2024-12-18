package com.msinfermiere.repository;

import com.msinfermiere.entity.Reparto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepartoRepository extends JpaRepository<Reparto, Long> {
}
