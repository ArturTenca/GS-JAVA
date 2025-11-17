package com.globalsolution.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trilhas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrilhaDeAprendizagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome da trilha é obrigatório")
    @Size(min = 5, max = 150, message = "Nome deve ter entre 5 e 150 caracteres")
    @Column(nullable = false, length = 150)
    private String nome;

    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    @Lob
    @Column(columnDefinition = "CLOB")
    private String descricao;

    @NotBlank(message = "Nível é obrigatório")
    @Column(nullable = false, length = 50)
    private String nivel; // INICIANTE, INTERMEDIARIO, AVANCADO

    @NotNull(message = "Carga horária é obrigatória")
    @Min(value = 1, message = "Carga horária deve ser maior que zero")
    @Column(name = "carga_horaria", nullable = false)
    private Integer cargaHoraria;

    @Size(max = 100, message = "Foco principal deve ter no máximo 100 caracteres")
    @Column(name = "foco_principal", length = 100)
    private String focoPrincipal;

    @ManyToMany
    @JoinTable(
        name = "trilha_competencia",
        joinColumns = @JoinColumn(name = "trilha_id"),
        inverseJoinColumns = @JoinColumn(name = "competencia_id")
    )
    private List<Competencia> competencias = new ArrayList<>();

    @OneToMany(mappedBy = "trilha", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Matricula> matriculas = new ArrayList<>();
}

