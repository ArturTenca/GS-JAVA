package com.globalsolution.domain.repository;

import com.globalsolution.domain.model.Habilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabilidadeRepository extends JpaRepository<Habilidade, Long> {
    List<Habilidade> findByTipo(String tipo);
    List<Habilidade> findByNomeContainingIgnoreCase(String nome);
}

