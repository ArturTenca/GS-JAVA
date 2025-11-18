package com.globalsolution.domain.service;

import com.globalsolution.api.dto.CompetenciaDTO;
import com.globalsolution.api.dto.TrilhaCreateDTO;
import com.globalsolution.api.dto.TrilhaDTO;
import com.globalsolution.domain.exception.TrilhaNaoEncontradaException;
import com.globalsolution.domain.model.Competencia;
import com.globalsolution.domain.model.TrilhaDeAprendizagem;
import com.globalsolution.domain.repository.CompetenciaRepository;
import com.globalsolution.domain.repository.TrilhaDeAprendizagemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrilhaDeAprendizagemService {

    @Autowired
    private TrilhaDeAprendizagemRepository trilhaRepository;

    @Autowired
    private CompetenciaRepository competenciaRepository;

    public Page<TrilhaDTO> listarTodas(Pageable pageable) {
        return trilhaRepository.findAll(pageable).map(this::toDTO);
    }

    public List<TrilhaDTO> listarTodas() {
        return trilhaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public TrilhaDeAprendizagem buscarPorId(Long id) {
        return trilhaRepository.findById(id)
                .orElseThrow(() -> new TrilhaNaoEncontradaException(id));
    }

    public TrilhaDTO buscarPorIdDTO(Long id) {
        TrilhaDeAprendizagem trilha = buscarPorId(id);
        return toDTO(trilha);
    }

    @Transactional
    public TrilhaDTO criar(@Valid TrilhaCreateDTO dto) {
        // Validação de nível
        String nivel = dto.getNivel().toUpperCase();
        if (!nivel.equals("INICIANTE") && !nivel.equals("INTERMEDIARIO") && !nivel.equals("AVANCADO")) {
            throw new IllegalArgumentException("Nível deve ser: INICIANTE, INTERMEDIARIO ou AVANCADO");
        }
        
        // Validação de carga horária negativa
        if (dto.getCargaHoraria() != null && dto.getCargaHoraria() <= 0) {
            throw new IllegalArgumentException("Carga horária deve ser maior que zero");
        }
        
        TrilhaDeAprendizagem trilha = toEntity(dto);
        trilha.setNivel(nivel);
        
        // Associar competências se fornecidas
        if (dto.getCompetenciaIds() != null && !dto.getCompetenciaIds().isEmpty()) {
            List<Competencia> competencias = competenciaRepository.findAllById(dto.getCompetenciaIds());
            trilha.setCompetencias(competencias);
        }
        
        TrilhaDeAprendizagem trilhaSalva = trilhaRepository.save(trilha);
        return toDTO(trilhaSalva);
    }

    @Transactional
    public TrilhaDTO atualizar(Long id, @Valid TrilhaCreateDTO dto) {
        TrilhaDeAprendizagem trilhaExistente = buscarPorId(id);

        // Validação de nível
        String nivel = dto.getNivel().toUpperCase();
        if (!nivel.equals("INICIANTE") && !nivel.equals("INTERMEDIARIO") && !nivel.equals("AVANCADO")) {
            throw new IllegalArgumentException("Nível deve ser: INICIANTE, INTERMEDIARIO ou AVANCADO");
        }

        // Validação de carga horária negativa
        if (dto.getCargaHoraria() != null && dto.getCargaHoraria() <= 0) {
            throw new IllegalArgumentException("Carga horária deve ser maior que zero");
        }

        trilhaExistente.setNome(dto.getNome());
        trilhaExistente.setDescricao(dto.getDescricao());
        trilhaExistente.setNivel(nivel);
        trilhaExistente.setCargaHoraria(dto.getCargaHoraria());
        trilhaExistente.setFocoPrincipal(dto.getFocoPrincipal());

        // Atualizar competências se fornecidas
        if (dto.getCompetenciaIds() != null && !dto.getCompetenciaIds().isEmpty()) {
            List<Competencia> competencias = competenciaRepository.findAllById(dto.getCompetenciaIds());
            trilhaExistente.setCompetencias(competencias);
        }

        TrilhaDeAprendizagem trilhaSalva = trilhaRepository.save(trilhaExistente);
        return toDTO(trilhaSalva);
    }

    private TrilhaDTO toDTO(TrilhaDeAprendizagem trilha) {
        TrilhaDTO dto = new TrilhaDTO();
        dto.setId(trilha.getId());
        dto.setNome(trilha.getNome());
        dto.setDescricao(trilha.getDescricao());
        dto.setNivel(trilha.getNivel());
        dto.setCargaHoraria(trilha.getCargaHoraria());
        dto.setFocoPrincipal(trilha.getFocoPrincipal());
        
        if (trilha.getCompetencias() != null) {
            dto.setCompetencias(trilha.getCompetencias().stream()
                    .map(c -> new CompetenciaDTO(c.getId(), c.getNome(), c.getCategoria(), c.getDescricao()))
                    .collect(Collectors.toList()));
        }
        
        return dto;
    }

    private TrilhaDeAprendizagem toEntity(TrilhaCreateDTO dto) {
        TrilhaDeAprendizagem trilha = new TrilhaDeAprendizagem();
        trilha.setNome(dto.getNome());
        trilha.setDescricao(dto.getDescricao());
        trilha.setNivel(dto.getNivel());
        trilha.setCargaHoraria(dto.getCargaHoraria());
        trilha.setFocoPrincipal(dto.getFocoPrincipal());
        return trilha;
    }

    @Transactional
    public void excluir(Long id) {
        TrilhaDeAprendizagem trilha = buscarPorId(id);
        trilhaRepository.delete(trilha);
    }

    public List<TrilhaDTO> buscarPorNivel(String nivel) {
        return trilhaRepository.findByNivel(nivel.toUpperCase()).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

