package com.mspazienti.repository.relation;

import com.mspazienti.entity.relation.MedicinaleCartella;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicinaleCartellaRepository extends JpaRepository<MedicinaleCartella, Integer> {
}
