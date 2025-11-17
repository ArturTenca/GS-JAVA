package com.globalsolution.domain.exception;

public class EmailJaCadastradoException extends RuntimeException {
    public EmailJaCadastradoException(String email) {
        super(String.format("Email %s já está cadastrado", email));
    }
}

