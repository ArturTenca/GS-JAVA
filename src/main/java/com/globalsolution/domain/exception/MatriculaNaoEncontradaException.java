package com.globalsolution.domain.exception;

public class MatriculaNaoEncontradaException extends RuntimeException {
    public MatriculaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public MatriculaNaoEncontradaException(Long id) {
        super(String.format("Matrícula com ID %d não encontrada", id));
    }
}

