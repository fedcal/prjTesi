package com.msinfermiere.repository;

import com.msinfermiere.entity.Infermiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InfermiereRepository extends JpaRepository<Infermiere, Integer>, JpaSpecificationExecutor<Infermiere> {
}
