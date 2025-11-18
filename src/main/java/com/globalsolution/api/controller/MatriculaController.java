package com.globalsolution.api.controller;

import com.globalsolution.api.dto.MatriculaCreateDTO;
import com.globalsolution.api.dto.MatriculaDTO;
import com.globalsolution.domain.service.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<Page<MatriculaDTO>> listarTodas(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<MatriculaDTO> matriculas = matriculaService.listarTodas(pageable);
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDTO> buscarPorId(@PathVariable Long id) {
        MatriculaDTO matricula = matriculaService.buscarPorIdDTO(id);
        return ResponseEntity.ok(matricula);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<MatriculaDTO>> buscarPorUsuario(@PathVariable Long usuarioId) {
        List<MatriculaDTO> matriculas = matriculaService.buscarPorUsuario(usuarioId);
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("/trilha/{trilhaId}")
    public ResponseEntity<List<MatriculaDTO>> buscarPorTrilha(@PathVariable Long trilhaId) {
        List<MatriculaDTO> matriculas = matriculaService.buscarPorTrilha(trilhaId);
        return ResponseEntity.ok(matriculas);
    }

    @PostMapping
    public ResponseEntity<MatriculaDTO> criar(@Valid @RequestBody MatriculaCreateDTO dto) {
        MatriculaDTO matricula = matriculaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(matricula);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<MatriculaDTO> atualizarStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String novoStatus = request.get("status");
        MatriculaDTO matricula = matriculaService.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok(matricula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        matriculaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}

