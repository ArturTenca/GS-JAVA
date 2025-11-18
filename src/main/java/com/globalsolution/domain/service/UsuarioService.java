package com.globalsolution.domain.service;

import com.globalsolution.api.dto.UsuarioCreateDTO;
import com.globalsolution.api.dto.UsuarioDTO;
import com.globalsolution.domain.exception.EmailJaCadastradoException;
import com.globalsolution.domain.exception.UsuarioNaoEncontradoException;
import com.globalsolution.domain.model.Matricula;
import com.globalsolution.domain.model.Usuario;
import com.globalsolution.domain.repository.MatriculaRepository;
import com.globalsolution.domain.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    public Page<UsuarioDTO> listarTodos(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(this::toDTO);
    }

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    public UsuarioDTO buscarPorIdDTO(Long id) {
        Usuario usuario = buscarPorId(id);
        return toDTO(usuario);
    }

    @Transactional
    public UsuarioDTO criar(@Valid UsuarioCreateDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new EmailJaCadastradoException(dto.getEmail());
        }
        
        Usuario usuario = toEntity(dto);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return toDTO(usuarioSalvo);
    }

    @Transactional
    public UsuarioDTO atualizar(Long id, @Valid UsuarioCreateDTO dto) {
        Usuario usuarioExistente = buscarPorId(id);
        
        // Verifica se o email está sendo alterado e se já existe
        if (!usuarioExistente.getEmail().equals(dto.getEmail()) &&
            usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new EmailJaCadastradoException(dto.getEmail());
        }

        usuarioExistente.setNome(dto.getNome());
        usuarioExistente.setEmail(dto.getEmail());
        usuarioExistente.setAreaAtuacao(dto.getAreaAtuacao());
        usuarioExistente.setNivelCarreira(dto.getNivelCarreira());

        Usuario usuarioSalvo = usuarioRepository.save(usuarioExistente);
        return toDTO(usuarioSalvo);
    }

    private UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getAreaAtuacao(),
                usuario.getNivelCarreira(),
                usuario.getDataCadastro()
        );
    }

    private Usuario toEntity(UsuarioCreateDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setAreaAtuacao(dto.getAreaAtuacao());
        usuario.setNivelCarreira(dto.getNivelCarreira());
        return usuario;
    }

    @Transactional
    public void excluir(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
        
        // Buscar e excluir todas as matrículas do usuário primeiro
        List<Matricula> matriculas = matriculaRepository.findByUsuarioId(id);
        if (!matriculas.isEmpty()) {
            matriculaRepository.deleteAll(matriculas);
        }
        
        // Excluir o usuário (o cascade também deve funcionar, mas garantimos a exclusão manual)
        usuarioRepository.delete(usuario);
    }
}

