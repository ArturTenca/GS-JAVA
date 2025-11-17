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
@Table(name = "competencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Competencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome da competência é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nome;

    @Size(max = 100, message = "Categoria deve ter no máximo 100 caracteres")
    @Column(length = 100)
    private String categoria;

    @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
    @Lob
    @Column(columnDefinition = "CLOB")
    private String descricao;

    @ManyToMany(mappedBy = "competencias")
    @JsonIgnore
    private List<TrilhaDeAprendizagem> trilhas = new ArrayList<>();
}

