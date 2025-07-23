package com.mspazienti.repository.operazione;

import com.mspazienti.entity.operazione.RefertoOperazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefertoOperazioneRepository extends JpaRepository<RefertoOperazione, Integer> {
}
