package com.globalsolution.api.controller;

import com.globalsolution.domain.model.Matricula;
import com.globalsolution.domain.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/matriculas")
@CrossOrigin(origins = "*")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping
    public ResponseEntity<List<Matricula>> listarTodas() {
        List<Matricula> matriculas = matriculaService.listarTodas();
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> buscarPorId(@PathVariable Long id) {
        Matricula matricula = matriculaService.buscarPorId(id);
        return ResponseEntity.ok(matricula);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Matricula>> buscarPorUsuario(@PathVariable Long usuarioId) {
        List<Matricula> matriculas = matriculaService.buscarPorUsuario(usuarioId);
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("/trilha/{trilhaId}")
    public ResponseEntity<List<Matricula>> buscarPorTrilha(@PathVariable Long trilhaId) {
        List<Matricula> matriculas = matriculaService.buscarPorTrilha(trilhaId);
        return ResponseEntity.ok(matriculas);
    }

    @PostMapping
    public ResponseEntity<Matricula> criar(@RequestBody Map<String, Long> request) {
        Long usuarioId = request.get("usuarioId");
        Long trilhaId = request.get("trilhaId");
        Matricula matricula = matriculaService.criar(usuarioId, trilhaId);
        return ResponseEntity.status(HttpStatus.CREATED).body(matricula);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Matricula> atualizarStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String novoStatus = request.get("status");
        Matricula matricula = matriculaService.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok(matricula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        matriculaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}

