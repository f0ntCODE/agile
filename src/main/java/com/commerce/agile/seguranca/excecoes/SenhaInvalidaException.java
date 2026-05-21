package com.commerce.agile.seguranca.excecoes;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException(String message) {
        super("[X]" + message);
    }
}
