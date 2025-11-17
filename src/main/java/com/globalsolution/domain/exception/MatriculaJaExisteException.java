package com.globalsolution.domain.exception;

public class MatriculaJaExisteException extends RuntimeException {
    public MatriculaJaExisteException(String mensagem) {
        super(mensagem);
    }
}

