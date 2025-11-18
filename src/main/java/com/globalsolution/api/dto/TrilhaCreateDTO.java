package com.globalsolution.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrilhaCreateDTO {
    @NotBlank(message = "Nome da trilha é obrigatório")
    @Size(min = 5, max = 150, message = "Nome deve ter entre 5 e 150 caracteres")
    private String nome;

    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    private String descricao;

    @NotBlank(message = "Nível é obrigatório")
    private String nivel;

    @NotNull(message = "Carga horária é obrigatória")
    @Min(value = 1, message = "Carga horária deve ser maior que zero")
    private Integer cargaHoraria;

    @Size(max = 100, message = "Foco principal deve ter no máximo 100 caracteres")
    private String focoPrincipal;

    private List<Long> competenciaIds = new ArrayList<>();
}

