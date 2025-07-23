package com.msmedico.repository.relation;

import com.msmedico.entity.relation.OperazioneCartella;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperazioneCartellaRepository extends JpaRepository<OperazioneCartella, Integer> {
}
