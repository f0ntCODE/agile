package com.commerce.agile.dominio;

import src.main.java.com.commerce.agile.seguranca.excecoes.NomeInvalidoException;

public class CategoriaDomain {

    private Long id;
    private String nome;

    public CategoriaDomain(Long id, String nome) {
        if(nome.isEmpty()){throw new NomeInvalidoException("Nome inválido");}

        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }
}
