package com.mspazienti.repository.relation;

import com.mspazienti.entity.relation.MalattiaCartella;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MalattiaCartellaRepository extends JpaRepository<MalattiaCartella, Integer> {
}
