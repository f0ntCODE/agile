package com.commerce.agile.dominio;

import src.main.java.com.commerce.agile.seguranca.excecoes.NomeInvalidoException;

/*
* DOMÍNIO CATEGORIA
* */

public class CategoriaDomain {

    private Long id;
    private String nome;

    public CategoriaDomain(String nome) {
        if(nome.isEmpty() || nome.matches(".*[0-9].*")){throw new NomeInvalidoException("Nome inválido");}

        this.nome = nome.toLowerCase();
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){this.id = id;}
}
