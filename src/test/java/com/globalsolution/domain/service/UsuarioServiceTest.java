package com.globalsolution.domain.service;

import com.globalsolution.api.dto.UsuarioCreateDTO;
import com.globalsolution.api.dto.UsuarioDTO;
import com.globalsolution.domain.exception.EmailJaCadastradoException;
import com.globalsolution.domain.exception.UsuarioNaoEncontradoException;
import com.globalsolution.domain.model.Usuario;
import com.globalsolution.domain.repository.MatriculaRepository;
import com.globalsolution.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private MatriculaRepository matriculaRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
    private UsuarioCreateDTO usuarioCreateDTO;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("João Silva");
        usuario.setEmail("joao@email.com");
        usuario.setAreaAtuacao("TI");
        usuario.setNivelCarreira("Júnior");
        usuario.setDataCadastro(LocalDate.now());

        usuarioCreateDTO = new UsuarioCreateDTO();
        usuarioCreateDTO.setNome("João Silva");
        usuarioCreateDTO.setEmail("joao@email.com");
        usuarioCreateDTO.setAreaAtuacao("TI");
        usuarioCreateDTO.setNivelCarreira("Júnior");
    }

    @Test
    void deveCriarUsuarioComSucesso() {
        // Arrange
        when(usuarioRepository.existsByEmail(anyString())).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        // Act
        UsuarioDTO resultado = usuarioService.criar(usuarioCreateDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals(usuario.getId(), resultado.getId());
        assertEquals(usuario.getNome(), resultado.getNome());
        assertEquals(usuario.getEmail(), resultado.getEmail());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaExiste() {
        // Arrange
        when(usuarioRepository.existsByEmail(anyString())).thenReturn(true);

        // Act & Assert
        assertThrows(EmailJaCadastradoException.class, () -> {
            usuarioService.criar(usuarioCreateDTO);
        });
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void deveBuscarUsuarioPorIdComSucesso() {
        // Arrange
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // Act
        UsuarioDTO resultado = usuarioService.buscarPorIdDTO(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(usuario.getId(), resultado.getId());
        assertEquals(usuario.getNome(), resultado.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        // Arrange
        when(usuarioRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            usuarioService.buscarPorIdDTO(999L);
        });
    }

    @Test
    void deveAtualizarUsuarioComSucesso() {
        // Arrange
        UsuarioCreateDTO dtoAtualizado = new UsuarioCreateDTO();
        dtoAtualizado.setNome("João Silva Atualizado");
        dtoAtualizado.setEmail("joao@email.com");
        dtoAtualizado.setAreaAtuacao("TI");
        dtoAtualizado.setNivelCarreira("Pleno");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.existsByEmail(anyString())).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        // Act
        UsuarioDTO resultado = usuarioService.atualizar(1L, dtoAtualizado);

        // Assert
        assertNotNull(resultado);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }
}

