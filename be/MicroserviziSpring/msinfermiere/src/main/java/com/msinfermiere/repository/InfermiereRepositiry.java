package com.msinfermiere.repository;

import com.msinfermiere.entity.Infermiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfermiereRepositiry extends JpaRepository<Infermiere, Long> {
}
