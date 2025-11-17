package com.globalsolution.api.controller;

import com.globalsolution.domain.model.TrilhaDeAprendizagem;
import com.globalsolution.domain.service.TrilhaDeAprendizagemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<TrilhaDeAprendizagem>> listarTodas() {
        List<TrilhaDeAprendizagem> trilhas = trilhaService.listarTodas();
        return ResponseEntity.ok(trilhas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrilhaDeAprendizagem> buscarPorId(@PathVariable Long id) {
        TrilhaDeAprendizagem trilha = trilhaService.buscarPorId(id);
        return ResponseEntity.ok(trilha);
    }

    @GetMapping("/nivel/{nivel}")
    public ResponseEntity<List<TrilhaDeAprendizagem>> buscarPorNivel(@PathVariable String nivel) {
        List<TrilhaDeAprendizagem> trilhas = trilhaService.buscarPorNivel(nivel);
        return ResponseEntity.ok(trilhas);
    }

    @PostMapping
    public ResponseEntity<TrilhaDeAprendizagem> criar(@Valid @RequestBody TrilhaDeAprendizagem trilha) {
        TrilhaDeAprendizagem trilhaCriada = trilhaService.criar(trilha);
        return ResponseEntity.status(HttpStatus.CREATED).body(trilhaCriada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrilhaDeAprendizagem> atualizar(@PathVariable Long id, @Valid @RequestBody TrilhaDeAprendizagem trilha) {
        TrilhaDeAprendizagem trilhaAtualizada = trilhaService.atualizar(id, trilha);
        return ResponseEntity.ok(trilhaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        trilhaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}

