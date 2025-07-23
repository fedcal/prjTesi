package com.msinfermiere.repository;

import com.msinfermiere.entity.Malattia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MalattiaRepository extends JpaRepository<Malattia, Integer> {
}
