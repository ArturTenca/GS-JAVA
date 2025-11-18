package com.globalsolution.api.controller;

import com.globalsolution.api.dto.TrilhaCreateDTO;
import com.globalsolution.api.dto.TrilhaDTO;
import com.globalsolution.domain.service.TrilhaDeAprendizagemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trilhas")
@CrossOrigin(origins = "*")
public class TrilhaDeAprendizagemController {

    @Autowired
    private TrilhaDeAprendizagemService trilhaService;

    @GetMapping
    public ResponseEntity<Page<TrilhaDTO>> listarTodas(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<TrilhaDTO> trilhas = trilhaService.listarTodas(pageable);
        return ResponseEntity.ok(trilhas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrilhaDTO> buscarPorId(@PathVariable Long id) {
        TrilhaDTO trilha = trilhaService.buscarPorIdDTO(id);
        return ResponseEntity.ok(trilha);
    }

    @GetMapping("/nivel/{nivel}")
    public ResponseEntity<List<TrilhaDTO>> buscarPorNivel(@PathVariable String nivel) {
        List<TrilhaDTO> trilhas = trilhaService.buscarPorNivel(nivel);
        return ResponseEntity.ok(trilhas);
    }

    @PostMapping
    public ResponseEntity<TrilhaDTO> criar(@Valid @RequestBody TrilhaCreateDTO dto) {
        TrilhaDTO trilhaCriada = trilhaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(trilhaCriada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrilhaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody TrilhaCreateDTO dto) {
        TrilhaDTO trilhaAtualizada = trilhaService.atualizar(id, dto);
        return ResponseEntity.ok(trilhaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        trilhaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}

