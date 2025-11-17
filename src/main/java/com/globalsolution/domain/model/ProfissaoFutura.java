package com.globalsolution.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profissoes_futuras")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfissaoFutura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome da profissão é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    @Column(nullable = false, unique = true, length = 100)
    private String nome;

    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    @Lob
    @Column(columnDefinition = "CLOB")
    private String descricao;

    @Size(max = 200, message = "Categoria deve ter no máximo 200 caracteres")
    @Column(length = 200)
    private String categoria; // Ex: Tecnologia, Saúde, Educação, etc.

    @OneToMany(mappedBy = "profissaoFutura", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CaminhoCarreira> caminhos = new ArrayList<>();
}

