package com.commerce.agile.infraestrutura.seguranca.excecoes;

public class IdadeException extends RuntimeException {
    public IdadeException(String message) {
        super("[X]" + message);
    }
}
