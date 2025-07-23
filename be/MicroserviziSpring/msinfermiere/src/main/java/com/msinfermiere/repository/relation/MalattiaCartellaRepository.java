package com.msinfermiere.repository.relation;

import com.msinfermiere.entity.relation.MalattiaCartella;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MalattiaCartellaRepository extends JpaRepository<MalattiaCartella, Integer> {
}
