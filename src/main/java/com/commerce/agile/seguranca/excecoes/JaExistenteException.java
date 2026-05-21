package com.commerce.agile.seguranca.excecoes;

public class JaExistenteException extends RuntimeException {
    public JaExistenteException(String message) {
        super("[X]" + message);
    }
}
