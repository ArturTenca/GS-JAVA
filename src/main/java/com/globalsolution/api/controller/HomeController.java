package com.globalsolution.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class HomeController {

    @GetMapping
    public ResponseEntity<Map<String, Object>> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("nome", "Global Solution 2025 - Plataforma de Upskilling/Reskilling");
        response.put("descricao", "API RESTful para plataforma de requalificação profissional voltada ao futuro do trabalho 2030+");
        response.put("versao", "1.0.0");
        
        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("Usuários", "/api/usuarios - CRUD completo de usuários");
        endpoints.put("Trilhas de Aprendizagem", "/api/trilhas - CRUD completo de trilhas");
        endpoints.put("Matrículas", "/api/matriculas - Gerenciamento de matrículas");
        endpoints.put("Documentação", "Consulte o README.md para exemplos de uso");
        
        response.put("endpoints", endpoints);
        
        Map<String, String> recursos = new HashMap<>();
        recursos.put("GET /api/usuarios", "Lista todos os usuários");
        recursos.put("GET /api/usuarios/{id}", "Busca usuário por ID");
        recursos.put("POST /api/usuarios", "Cria novo usuário");
        recursos.put("PUT /api/usuarios/{id}", "Atualiza usuário");
        recursos.put("DELETE /api/usuarios/{id}", "Remove usuário");
        recursos.put("GET /api/trilhas", "Lista todas as trilhas");
        recursos.put("GET /api/trilhas/{id}", "Busca trilha por ID");
        recursos.put("GET /api/trilhas/nivel/{nivel}", "Busca trilhas por nível");
        recursos.put("POST /api/trilhas", "Cria nova trilha");
        recursos.put("PUT /api/trilhas/{id}", "Atualiza trilha");
        recursos.put("DELETE /api/trilhas/{id}", "Remove trilha");
        recursos.put("GET /api/matriculas", "Lista todas as matrículas");
        recursos.put("GET /api/matriculas/{id}", "Busca matrícula por ID");
        recursos.put("GET /api/matriculas/usuario/{usuarioId}", "Busca matrículas do usuário");
        recursos.put("GET /api/matriculas/trilha/{trilhaId}", "Busca matrículas da trilha");
        recursos.put("POST /api/matriculas", "Cria nova matrícula");
        recursos.put("PUT /api/matriculas/{id}/status", "Atualiza status da matrícula");
        recursos.put("DELETE /api/matriculas/{id}", "Remove matrícula");
        
        response.put("recursos", recursos);
        
        return ResponseEntity.ok(response);
    }
}

