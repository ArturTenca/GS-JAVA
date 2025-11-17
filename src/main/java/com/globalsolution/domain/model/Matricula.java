package com.globalsolution.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "matriculas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Usuário é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnoreProperties({"matriculas"})
    private Usuario usuario;

    @NotNull(message = "Trilha é obrigatória")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trilha_id", nullable = false)
    @JsonIgnoreProperties({"matriculas", "competencias"})
    private TrilhaDeAprendizagem trilha;

    @Column(name = "data_inscricao", nullable = false)
    private LocalDate dataInscricao;

    @NotBlank(message = "Status é obrigatório")
    @Column(nullable = false, length = 50)
    private String status; // ATIVA, CONCLUIDA, CANCELADA

    @PrePersist
    protected void onCreate() {
        if (dataInscricao == null) {
            dataInscricao = LocalDate.now();
        }
        if (status == null || status.isEmpty()) {
            status = "ATIVA";
        }
    }
}

