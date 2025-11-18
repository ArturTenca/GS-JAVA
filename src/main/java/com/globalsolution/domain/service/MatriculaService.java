package com.globalsolution.domain.service;

import com.globalsolution.api.dto.MatriculaCreateDTO;
import com.globalsolution.api.dto.MatriculaDTO;
import com.globalsolution.domain.exception.MatriculaJaExisteException;
import com.globalsolution.domain.exception.MatriculaNaoEncontradaException;
import com.globalsolution.domain.exception.TrilhaNaoEncontradaException;
import com.globalsolution.domain.exception.UsuarioNaoEncontradoException;
import com.globalsolution.domain.model.Matricula;
import com.globalsolution.domain.model.TrilhaDeAprendizagem;
import com.globalsolution.domain.model.Usuario;
import com.globalsolution.domain.repository.MatriculaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TrilhaDeAprendizagemService trilhaService;

    public Page<MatriculaDTO> listarTodas(Pageable pageable) {
        return matriculaRepository.findAll(pageable).map(this::toDTO);
    }

    @Transactional(readOnly = true)
    public List<MatriculaDTO> listarTodas() {
        return matriculaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Matricula buscarPorId(Long id) {
        return matriculaRepository.findById(id)
                .orElseThrow(() -> new MatriculaNaoEncontradaException(id));
    }

    public MatriculaDTO buscarPorIdDTO(Long id) {
        Matricula matricula = buscarPorId(id);
        return toDTO(matricula);
    }

    @Transactional
    public MatriculaDTO criar(@Valid MatriculaCreateDTO dto) {
        Usuario usuario = usuarioService.buscarPorId(dto.getUsuarioId());
        TrilhaDeAprendizagem trilha = trilhaService.buscarPorId(dto.getTrilhaId());

        // Verifica se já existe matrícula ativa
        matriculaRepository.findByUsuarioIdAndTrilhaId(dto.getUsuarioId(), dto.getTrilhaId())
                .ifPresent(matricula -> {
                    if ("ATIVA".equals(matricula.getStatus())) {
                        throw new MatriculaJaExisteException(
                                String.format("Usuário já está matriculado nesta trilha com status ATIVA")
                        );
                    }
                });

        Matricula matricula = new Matricula();
        matricula.setUsuario(usuario);
        matricula.setTrilha(trilha);
        matricula.setStatus("ATIVA");

        Matricula matriculaSalva = matriculaRepository.save(matricula);
        return toDTO(matriculaSalva);
    }

    @Transactional
    public MatriculaDTO atualizarStatus(Long id, String novoStatus) {
        Matricula matricula = buscarPorId(id);
        matricula.setStatus(novoStatus);
        Matricula matriculaSalva = matriculaRepository.save(matricula);
        return toDTO(matriculaSalva);
    }

    private MatriculaDTO toDTO(Matricula matricula) {
        MatriculaDTO dto = new MatriculaDTO();
        dto.setId(matricula.getId());
        dto.setUsuarioId(matricula.getUsuario().getId());
        dto.setUsuarioNome(matricula.getUsuario().getNome());
        dto.setUsuarioEmail(matricula.getUsuario().getEmail());
        dto.setTrilhaId(matricula.getTrilha().getId());
        dto.setTrilhaNome(matricula.getTrilha().getNome());
        dto.setDataInscricao(matricula.getDataInscricao());
        dto.setStatus(matricula.getStatus());
        return dto;
    }

    @Transactional
    public void excluir(Long id) {
        Matricula matricula = buscarPorId(id);
        matriculaRepository.delete(matricula);
    }

    public List<MatriculaDTO> buscarPorUsuario(Long usuarioId) {
        return matriculaRepository.findByUsuarioId(usuarioId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<MatriculaDTO> buscarPorTrilha(Long trilhaId) {
        return matriculaRepository.findByTrilhaId(trilhaId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

