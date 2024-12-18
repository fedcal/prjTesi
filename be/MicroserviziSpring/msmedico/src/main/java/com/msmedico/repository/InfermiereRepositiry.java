package com.msmedico.repository;

import com.msmedico.entity.Infermiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfermiereRepositiry extends JpaRepository<Infermiere, Long> {
}
