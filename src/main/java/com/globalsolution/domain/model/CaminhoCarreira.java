package com.globalsolution.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "caminhos_carreira")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaminhoCarreira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Profissão atual é obrigatória")
    @Column(name = "profissao_atual", nullable = false, length = 100)
    private String profissaoAtual;

    @NotNull(message = "Profissão futura é obrigatória")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profissao_futura_id", nullable = false)
    @JsonIgnoreProperties({"caminhos"})
    private ProfissaoFutura profissaoFutura;

    @ManyToMany
    @JoinTable(
        name = "caminho_habilidade",
        joinColumns = @JoinColumn(name = "caminho_id"),
        inverseJoinColumns = @JoinColumn(name = "habilidade_id")
    )
    @JsonIgnoreProperties({"caminhos"})
    private List<Habilidade> habilidades = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trilha_id")
    @JsonIgnoreProperties({"matriculas", "competencias"})
    private TrilhaDeAprendizagem trilha;
}

