package com.globalsolution.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaDTO {
    private Long id;
    private Long usuarioId;
    private String usuarioNome;
    private String usuarioEmail;
    private Long trilhaId;
    private String trilhaNome;
    private LocalDate dataInscricao;
    private String status;
}

