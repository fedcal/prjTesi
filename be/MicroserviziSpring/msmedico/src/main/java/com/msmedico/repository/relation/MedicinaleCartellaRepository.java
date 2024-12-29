package com.msmedico.repository.relation;

import com.msmedico.entity.relation.MedicinaleCartella;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicinaleCartellaRepository extends JpaRepository<MedicinaleCartella, Integer> {
}
