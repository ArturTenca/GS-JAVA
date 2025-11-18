package com.globalsolution.domain.service;

import com.globalsolution.api.dto.TrilhaCreateDTO;
import com.globalsolution.api.dto.TrilhaDTO;
import com.globalsolution.domain.exception.TrilhaNaoEncontradaException;
import com.globalsolution.domain.model.TrilhaDeAprendizagem;
import com.globalsolution.domain.repository.CompetenciaRepository;
import com.globalsolution.domain.repository.TrilhaDeAprendizagemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrilhaDeAprendizagemServiceTest {

    @Mock
    private TrilhaDeAprendizagemRepository trilhaRepository;

    @Mock
    private CompetenciaRepository competenciaRepository;

    @InjectMocks
    private TrilhaDeAprendizagemService trilhaService;

    private TrilhaDeAprendizagem trilha;
    private TrilhaCreateDTO trilhaCreateDTO;

    @BeforeEach
    void setUp() {
        trilha = new TrilhaDeAprendizagem();
        trilha.setId(1L);
        trilha.setNome("Trilha de IA Generativa");
        trilha.setDescricao("Aprenda IA generativa");
        trilha.setNivel("INICIANTE");
        trilha.setCargaHoraria(80);
        trilha.setFocoPrincipal("Inteligência Artificial");
        trilha.setCompetencias(new ArrayList<>());

        trilhaCreateDTO = new TrilhaCreateDTO();
        trilhaCreateDTO.setNome("Trilha de IA Generativa");
        trilhaCreateDTO.setDescricao("Aprenda IA generativa");
        trilhaCreateDTO.setNivel("INICIANTE");
        trilhaCreateDTO.setCargaHoraria(80);
        trilhaCreateDTO.setFocoPrincipal("Inteligência Artificial");
        trilhaCreateDTO.setCompetenciaIds(new ArrayList<>());
    }

    @Test
    void deveCriarTrilhaComSucesso() {
        // Arrange
        when(trilhaRepository.save(any(TrilhaDeAprendizagem.class))).thenReturn(trilha);

        // Act
        TrilhaDTO resultado = trilhaService.criar(trilhaCreateDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals(trilha.getId(), resultado.getId());
        assertEquals(trilha.getNome(), resultado.getNome());
        assertEquals(trilha.getNivel(), resultado.getNivel());
        verify(trilhaRepository, times(1)).save(any(TrilhaDeAprendizagem.class));
    }

    @Test
    void deveLancarExcecaoQuandoCargaHorariaNegativa() {
        // Arrange
        trilhaCreateDTO.setCargaHoraria(-10);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            trilhaService.criar(trilhaCreateDTO);
        });
        verify(trilhaRepository, never()).save(any(TrilhaDeAprendizagem.class));
    }

    @Test
    void deveLancarExcecaoQuandoCargaHorariaZero() {
        // Arrange
        trilhaCreateDTO.setCargaHoraria(0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            trilhaService.criar(trilhaCreateDTO);
        });
        verify(trilhaRepository, never()).save(any(TrilhaDeAprendizagem.class));
    }

    @Test
    void deveLancarExcecaoQuandoNivelInvalido() {
        // Arrange
        trilhaCreateDTO.setNivel("INVALIDO");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            trilhaService.criar(trilhaCreateDTO);
        });
        verify(trilhaRepository, never()).save(any(TrilhaDeAprendizagem.class));
    }

    @Test
    void deveBuscarTrilhaPorIdComSucesso() {
        // Arrange
        when(trilhaRepository.findById(1L)).thenReturn(Optional.of(trilha));

        // Act
        TrilhaDTO resultado = trilhaService.buscarPorIdDTO(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(trilha.getId(), resultado.getId());
        assertEquals(trilha.getNome(), resultado.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoTrilhaNaoEncontrada() {
        // Arrange
        when(trilhaRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(TrilhaNaoEncontradaException.class, () -> {
            trilhaService.buscarPorIdDTO(999L);
        });
    }
}

