package com.commerce.agile.seguranca.excecoes;

public class IdadeException extends RuntimeException {
    public IdadeException(String message) {
        super("[X]" + message);
    }
}
