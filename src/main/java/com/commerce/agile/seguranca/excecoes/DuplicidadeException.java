package com.commerce.agile.seguranca.excecoes;

public class DuplicidadeException extends RuntimeException {
    public DuplicidadeException(String message) {
        super("[!]" + message);
    }
}
