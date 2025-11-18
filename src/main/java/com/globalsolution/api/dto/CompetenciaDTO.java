package com.globalsolution.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetenciaDTO {
    private Long id;
    private String nome;
    private String categoria;
    private String descricao;
}

