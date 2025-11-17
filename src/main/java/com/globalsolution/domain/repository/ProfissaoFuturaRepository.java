package com.globalsolution.domain.repository;

import com.globalsolution.domain.model.ProfissaoFutura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfissaoFuturaRepository extends JpaRepository<ProfissaoFutura, Long> {
    Optional<ProfissaoFutura> findByNomeIgnoreCase(String nome);
    List<ProfissaoFutura> findByCategoria(String categoria);
    List<ProfissaoFutura> findByNomeContainingIgnoreCase(String nome);
}

