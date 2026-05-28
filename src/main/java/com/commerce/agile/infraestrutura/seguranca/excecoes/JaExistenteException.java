package com.commerce.agile.infraestrutura.seguranca.excecoes;

public class JaExistenteException extends RuntimeException {
    public JaExistenteException(String message) {
        super("[X]" + message);
    }
}
