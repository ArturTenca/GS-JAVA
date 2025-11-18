package com.globalsolution.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrilhaDTO {
    private Long id;
    private String nome;
    private String descricao;
    private String nivel;
    private Integer cargaHoraria;
    private String focoPrincipal;
    private List<CompetenciaDTO> competencias = new ArrayList<>();
}

