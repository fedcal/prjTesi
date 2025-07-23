package com.msinfermiere.repository.relation;

import com.msinfermiere.entity.relation.OperazioneCartella;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperazioneCartellaRepository extends JpaRepository<OperazioneCartella, Integer> {
}
