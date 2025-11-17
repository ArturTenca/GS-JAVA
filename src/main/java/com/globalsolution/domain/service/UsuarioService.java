package com.globalsolution.domain.service;

import com.globalsolution.domain.exception.EmailJaCadastradoException;
import com.globalsolution.domain.exception.UsuarioNaoEncontradoException;
import com.globalsolution.domain.model.Usuario;
import com.globalsolution.domain.repository.MatriculaRepository;
import com.globalsolution.domain.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    @Transactional
    public Usuario criar(@Valid Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new EmailJaCadastradoException(usuario.getEmail());
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(Long id, @Valid Usuario usuarioAtualizado) {
        Usuario usuarioExistente = buscarPorId(id);
        
        // Verifica se o email está sendo alterado e se já existe
        if (!usuarioExistente.getEmail().equals(usuarioAtualizado.getEmail()) &&
            usuarioRepository.existsByEmail(usuarioAtualizado.getEmail())) {
            throw new EmailJaCadastradoException(usuarioAtualizado.getEmail());
        }

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setAreaAtuacao(usuarioAtualizado.getAreaAtuacao());
        usuarioExistente.setNivelCarreira(usuarioAtualizado.getNivelCarreira());

        return usuarioRepository.save(usuarioExistente);
    }

    @Transactional
    public void excluir(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
        
        // Excluir todas as matrículas associadas primeiro
        matriculaRepository.findByUsuarioId(id).forEach(matricula -> {
            matriculaRepository.delete(matricula);
        });
        matriculaRepository.flush();
        
        // Agora excluir o usuário
        usuarioRepository.delete(usuario);
        usuarioRepository.flush();
    }
}

