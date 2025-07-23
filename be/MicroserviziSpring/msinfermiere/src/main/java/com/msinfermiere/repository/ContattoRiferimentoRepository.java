package com.msinfermiere.repository;

import com.msinfermiere.entity.ContattoRiferimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContattoRiferimentoRepository extends JpaRepository<ContattoRiferimento, Integer> {
}
