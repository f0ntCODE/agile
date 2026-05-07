package com.commerce.agile.dominio;
import java.math.BigDecimal;

import src.main.java.com.commerce.agile.seguranca.excecoes.NomeInvalidoException;

/**
 * DOMÍNIO MERCADORIA
 */
public class MercadoriaDomain {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal precoUnitario;
    private CategoriaDomain categoria;

    public MercadoriaDomain(String nome, String descricao, BigDecimal precoUnitario){

        if(nome == null || nome.isBlank()){throw new NomeInvalidoException("[X] Nome inválido ou vazio");}
        if(descricao == null || descricao.isBlank()){throw new NomeInvalidoException("[X] Descricao inválida ou vazia");}
        if(precoUnitario.equals(BigDecimal.ZERO) || precoUnitario.compareTo(BigDecimal.ZERO) < 0){throw  new IllegalArgumentException("[X] Preco inválido para mercadoria");};
        this.nome = nome;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
    }

    public void atualizarDados(String novoNome, String novaDescricao, BigDecimal novoPrecoUnitario){
        if(novoNome == null || novoNome.isBlank()){throw new NomeInvalidoException("[X] Nome inválido ou vazio");}
        if(novaDescricao == null || novaDescricao.isBlank()){throw new NomeInvalidoException("[X] Descricao inválida ou vazia");}
        if(novoPrecoUnitario.equals(BigDecimal.ZERO) || novoPrecoUnitario.compareTo(BigDecimal.ZERO) < 0){throw  new IllegalArgumentException("[X] Preco inválido para mercadoria");}

        this.nome = novoNome;
        this.descricao = novaDescricao;
        this.precoUnitario = novoPrecoUnitario;
    }

    public Long getId() {return id;}

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public CategoriaDomain getCategoria() {
        return this.categoria;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setCategoria(CategoriaDomain categoria) {
        this.categoria = categoria;
    }
}
