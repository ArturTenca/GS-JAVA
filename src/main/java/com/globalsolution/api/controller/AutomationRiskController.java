package com.globalsolution.api.controller;

import com.globalsolution.domain.service.AutomationRiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/automation-risk")
@CrossOrigin(origins = "*")
public class AutomationRiskController {

    @Autowired
    private AutomationRiskService automationRiskService;

    @GetMapping("/analisar")
    public ResponseEntity<Map<String, Object>> analisarRisco(
            @RequestParam String profissao) {
        Map<String, Object> resultado = automationRiskService.analisarRiscoAutomacao(profissao);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/profissoes")
    public ResponseEntity<List<String>> listarProfissoes() {
        List<String> profissoes = automationRiskService.buscarNomesProfissoes();
        return ResponseEntity.ok(profissoes);
    }
}


