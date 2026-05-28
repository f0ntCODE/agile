package com.commerce.agile.infraestrutura.seguranca.excecoes;

public class NaoEncontradoException extends RuntimeException {
    public NaoEncontradoException(String message) {
        super("[X]" + message);
    }
}
