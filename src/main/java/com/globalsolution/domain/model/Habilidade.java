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
@Table(name = "habilidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Habilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome da habilidade é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nome;

    @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
    @Lob
    @Column(columnDefinition = "CLOB")
    private String descricao;

    @Size(max = 100, message = "Tipo deve ter no máximo 100 caracteres")
    @Column(length = 100)
    private String tipo; // EXIGIDA ou TRANSICAO

    @ManyToMany(mappedBy = "habilidades")
    @JsonIgnore
    private List<CaminhoCarreira> caminhos = new ArrayList<>();
}

