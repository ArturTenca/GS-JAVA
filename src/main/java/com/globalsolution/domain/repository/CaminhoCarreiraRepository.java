package com.globalsolution.domain.repository;

import com.globalsolution.domain.model.CaminhoCarreira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaminhoCarreiraRepository extends JpaRepository<CaminhoCarreira, Long> {
    List<CaminhoCarreira> findByProfissaoAtualIgnoreCase(String profissaoAtual);
    
    @Query("SELECT DISTINCT c FROM CaminhoCarreira c " +
           "LEFT JOIN FETCH c.profissaoFutura " +
           "LEFT JOIN FETCH c.habilidades " +
           "LEFT JOIN FETCH c.trilha " +
           "WHERE LOWER(c.profissaoAtual) = LOWER(:profissaoAtual)")
    List<CaminhoCarreira> findByProfissaoAtualWithRelations(String profissaoAtual);
    
    @Query("SELECT DISTINCT c.profissaoAtual FROM CaminhoCarreira c ORDER BY c.profissaoAtual")
    List<String> findDistinctProfissoesAtuais();
}

