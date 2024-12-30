package com.msinfermiere.repository.operazione;

import com.msinfermiere.entity.operazione.RefertoOperazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefertoOperazioneRepository extends JpaRepository<RefertoOperazione, Integer> {
}
