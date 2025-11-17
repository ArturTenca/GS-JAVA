package com.globalsolution.domain.service;

import com.globalsolution.domain.exception.TrilhaNaoEncontradaException;
import com.globalsolution.domain.model.TrilhaDeAprendizagem;
import com.globalsolution.domain.repository.TrilhaDeAprendizagemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrilhaDeAprendizagemService {

    @Autowired
    private TrilhaDeAprendizagemRepository trilhaRepository;

    public List<TrilhaDeAprendizagem> listarTodas() {
        return trilhaRepository.findAll();
    }

    public TrilhaDeAprendizagem buscarPorId(Long id) {
        return trilhaRepository.findById(id)
                .orElseThrow(() -> new TrilhaNaoEncontradaException(id));
    }

    @Transactional
    public TrilhaDeAprendizagem criar(@Valid TrilhaDeAprendizagem trilha) {
        // Validação de nível
        String nivel = trilha.getNivel().toUpperCase();
        if (!nivel.equals("INICIANTE") && !nivel.equals("INTERMEDIARIO") && !nivel.equals("AVANCADO")) {
            throw new IllegalArgumentException("Nível deve ser: INICIANTE, INTERMEDIARIO ou AVANCADO");
        }
        trilha.setNivel(nivel);
        return trilhaRepository.save(trilha);
    }

    @Transactional
    public TrilhaDeAprendizagem atualizar(Long id, @Valid TrilhaDeAprendizagem trilhaAtualizada) {
        TrilhaDeAprendizagem trilhaExistente = buscarPorId(id);

        // Validação de nível
        String nivel = trilhaAtualizada.getNivel().toUpperCase();
        if (!nivel.equals("INICIANTE") && !nivel.equals("INTERMEDIARIO") && !nivel.equals("AVANCADO")) {
            throw new IllegalArgumentException("Nível deve ser: INICIANTE, INTERMEDIARIO ou AVANCADO");
        }

        trilhaExistente.setNome(trilhaAtualizada.getNome());
        trilhaExistente.setDescricao(trilhaAtualizada.getDescricao());
        trilhaExistente.setNivel(nivel);
        trilhaExistente.setCargaHoraria(trilhaAtualizada.getCargaHoraria());
        trilhaExistente.setFocoPrincipal(trilhaAtualizada.getFocoPrincipal());

        return trilhaRepository.save(trilhaExistente);
    }

    @Transactional
    public void excluir(Long id) {
        TrilhaDeAprendizagem trilha = buscarPorId(id);
        trilhaRepository.delete(trilha);
    }

    public List<TrilhaDeAprendizagem> buscarPorNivel(String nivel) {
        return trilhaRepository.findByNivel(nivel.toUpperCase());
    }
}

