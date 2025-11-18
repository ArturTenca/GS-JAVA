package com.globalsolution.domain.repository;

import com.globalsolution.domain.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    @Query("SELECT DISTINCT m FROM Matricula m JOIN FETCH m.usuario JOIN FETCH m.trilha")
    List<Matricula> findAll();
    
    @Query("SELECT m FROM Matricula m WHERE m.usuario.id = :usuarioId")
    List<Matricula> findByUsuarioId(Long usuarioId);
    
    @Query("SELECT m FROM Matricula m WHERE m.trilha.id = :trilhaId")
    List<Matricula> findByTrilhaId(Long trilhaId);
    
    @Query("SELECT m FROM Matricula m WHERE m.usuario.id = :usuarioId AND m.trilha.id = :trilhaId")
    Optional<Matricula> findByUsuarioIdAndTrilhaId(Long usuarioId, Long trilhaId);
    
    List<Matricula> findByStatus(String status);
}

