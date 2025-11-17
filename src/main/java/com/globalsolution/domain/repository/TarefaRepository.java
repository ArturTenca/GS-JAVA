package com.globalsolution.domain.repository;

import com.globalsolution.domain.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByProfissaoId(Long profissaoId);
    
    @Query("SELECT t FROM Tarefa t JOIN FETCH t.profissao LEFT JOIN FETCH t.trilhaSugerida WHERE t.profissao.id = :profissaoId")
    List<Tarefa> findByProfissaoIdWithRelations(Long profissaoId);
}


