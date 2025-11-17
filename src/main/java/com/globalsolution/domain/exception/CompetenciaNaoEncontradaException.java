package com.globalsolution.domain.exception;

public class CompetenciaNaoEncontradaException extends RuntimeException {
    public CompetenciaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public CompetenciaNaoEncontradaException(Long id) {
        super(String.format("Competência com ID %d não encontrada", id));
    }
}

