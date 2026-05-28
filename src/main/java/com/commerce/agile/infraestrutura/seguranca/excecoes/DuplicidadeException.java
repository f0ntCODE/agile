package com.commerce.agile.infraestrutura.seguranca.excecoes;

public class DuplicidadeException extends RuntimeException {
    public DuplicidadeException(String message) {
        super("[!]" + message);
    }
}
