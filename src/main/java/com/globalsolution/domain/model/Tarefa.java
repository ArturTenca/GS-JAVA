package com.globalsolution.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tarefas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome da tarefa é obrigatório")
    @Size(min = 3, max = 200, message = "Nome deve ter entre 3 e 200 caracteres")
    @Column(nullable = false, length = 200)
    private String nome;

    @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
    @Lob
    @Column(columnDefinition = "CLOB")
    private String descricao;

    @NotNull(message = "Risco de automação é obrigatório")
    @Min(value = 0, message = "Risco deve ser entre 0 e 100")
    @Max(value = 100, message = "Risco deve ser entre 0 e 100")
    @Column(name = "risco_automacao", nullable = false)
    private Integer riscoAutomacao; // 0-100

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profissao_id", nullable = false)
    @JsonIgnoreProperties({"tarefas"})
    private Profissao profissao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trilha_sugerida_id")
    @JsonIgnoreProperties({"matriculas", "competencias"})
    private TrilhaDeAprendizagem trilhaSugerida;
}

