package com.globalsolution.domain.repository;

import com.globalsolution.domain.model.TrilhaDeAprendizagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrilhaDeAprendizagemRepository extends JpaRepository<TrilhaDeAprendizagem, Long> {
    List<TrilhaDeAprendizagem> findByNivel(String nivel);
    List<TrilhaDeAprendizagem> findByFocoPrincipalContainingIgnoreCase(String foco);
}

