package com.msmedico.repository;

import com.msmedico.entity.ContattoRiferimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContattoRiferimentoRepository extends JpaRepository<ContattoRiferimento, Integer> {
}
