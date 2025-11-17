package com.globalsolution.domain.exception;

public class TrilhaNaoEncontradaException extends RuntimeException {
    public TrilhaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public TrilhaNaoEncontradaException(Long id) {
        super(String.format("Trilha de Aprendizagem com ID %d não encontrada", id));
    }
}

