package com.commerce.agile.seguranca.excecoes;

public class EmaiIInvalidoException extends RuntimeException {
    public EmaiIInvalidoException(String message) {
        super("[X]" + message);
    }
}
