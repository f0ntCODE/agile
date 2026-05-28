package com.commerce.agile.infraestrutura.seguranca.excecoes;

public class EmaiIInvalidoException extends RuntimeException {
    public EmaiIInvalidoException(String message) {
        super("[X]" + message);
    }
}
