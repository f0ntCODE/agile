package com.commerce.agile.seguranca.excecoes;

public class CpfInvalidoException extends IllegalArgumentException {
    public CpfInvalidoException(String message) {
        super("[X]" + message);
    }
}
