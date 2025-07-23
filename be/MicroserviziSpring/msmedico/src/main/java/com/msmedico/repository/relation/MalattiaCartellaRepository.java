package com.msmedico.repository.relation;

import com.msmedico.entity.relation.MalattiaCartella;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MalattiaCartellaRepository extends JpaRepository<MalattiaCartella, Integer> {
}
