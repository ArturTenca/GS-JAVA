package com.globalsolution.domain.service;

import com.globalsolution.domain.exception.MatriculaJaExisteException;
import com.globalsolution.domain.exception.MatriculaNaoEncontradaException;
import com.globalsolution.domain.exception.TrilhaNaoEncontradaException;
import com.globalsolution.domain.exception.UsuarioNaoEncontradoException;
import com.globalsolution.domain.model.Matricula;
import com.globalsolution.domain.model.TrilhaDeAprendizagem;
import com.globalsolution.domain.model.Usuario;
import com.globalsolution.domain.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TrilhaDeAprendizagemService trilhaService;

    @Transactional(readOnly = true)
    public List<Matricula> listarTodas() {
        return matriculaRepository.findAll();
    }

    public Matricula buscarPorId(Long id) {
        return matriculaRepository.findById(id)
                .orElseThrow(() -> new MatriculaNaoEncontradaException(id));
    }

    @Transactional
    public Matricula criar(Long usuarioId, Long trilhaId) {
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        TrilhaDeAprendizagem trilha = trilhaService.buscarPorId(trilhaId);

        // Verifica se já existe matrícula ativa
        matriculaRepository.findByUsuarioIdAndTrilhaId(usuarioId, trilhaId)
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

        return matriculaRepository.save(matricula);
    }

    @Transactional
    public Matricula atualizarStatus(Long id, String novoStatus) {
        Matricula matricula = buscarPorId(id);
        matricula.setStatus(novoStatus);
        return matriculaRepository.save(matricula);
    }

    @Transactional
    public void excluir(Long id) {
        Matricula matricula = buscarPorId(id);
        matriculaRepository.delete(matricula);
    }

    public List<Matricula> buscarPorUsuario(Long usuarioId) {
        return matriculaRepository.findByUsuarioId(usuarioId);
    }

    public List<Matricula> buscarPorTrilha(Long trilhaId) {
        return matriculaRepository.findByTrilhaId(trilhaId);
    }
}

