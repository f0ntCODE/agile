package com.commerce.agile.infraestrutura.seguranca.excecoes;

public class NomeInvalidoException extends IllegalArgumentException {

    public NomeInvalidoException(String mensagem) {
        super(mensagem);
    }

    public NomeInvalidoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
