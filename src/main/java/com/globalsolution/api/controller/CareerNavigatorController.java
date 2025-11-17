package com.globalsolution.api.controller;

import com.globalsolution.domain.model.ProfissaoFutura;
import com.globalsolution.domain.service.CareerNavigatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/career-navigator")
@CrossOrigin(origins = "*")
public class CareerNavigatorController {

    @Autowired
    private CareerNavigatorService careerNavigatorService;

    @GetMapping("/navegar")
    public ResponseEntity<Map<String, Object>> navegarCarreira(
            @RequestParam String profissaoAtual) {
        Map<String, Object> resultado = careerNavigatorService.navegarCarreira(profissaoAtual);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/profissoes-futuras")
    public ResponseEntity<List<ProfissaoFutura>> listarProfissoesFuturas() {
        List<ProfissaoFutura> profissoes = careerNavigatorService.listarTodasProfissoesFuturas();
        return ResponseEntity.ok(profissoes);
    }

    @GetMapping("/profissoes-atuais")
    public ResponseEntity<List<String>> listarProfissoesAtuais() {
        List<String> profissoes = careerNavigatorService.listarProfissoesAtuais();
        return ResponseEntity.ok(profissoes);
    }
}

