package com.globalsolution.domain.repository;

import com.globalsolution.domain.model.Profissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfissaoRepository extends JpaRepository<Profissao, Long> {
    Optional<Profissao> findByNomeIgnoreCase(String nome);
    List<Profissao> findByNomeContainingIgnoreCase(String nome);
    
    @Query("SELECT DISTINCT p FROM Profissao p LEFT JOIN FETCH p.tarefas")
    List<Profissao> findAllWithTarefas();
}


