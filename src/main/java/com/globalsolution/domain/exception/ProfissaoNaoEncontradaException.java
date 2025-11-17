package com.globalsolution.domain.exception;

public class ProfissaoNaoEncontradaException extends RuntimeException {
    public ProfissaoNaoEncontradaException(String nome) {
        super(String.format("Profissão '%s' não encontrada", nome));
    }
}

