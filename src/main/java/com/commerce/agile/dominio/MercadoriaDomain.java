package com.commerce.agile.dominio;
import java.math.BigDecimal;

import com.commerce.agile.entidade.Categoria;
import src.main.java.com.commerce.agile.seguranca.excecoes.NomeInvalidoException;

/**
 * Entidade MERCADORIA
 */
public class MercadoriaDomain {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal precoUnitario;
    private CategoriaDomain categoriaDomain;

    public MercadoriaDomain(Long id, String nome, String descricao, BigDecimal precoUnitario, CategoriaDomain categoriaDomain){

        if(nome == null || nome.isBlank()){throw new NomeInvalidoException("[X] Nome inválido ou vazio");}
        if(descricao == null || descricao.isBlank()){throw new NomeInvalidoException("[X] Descricao inválida ou vazia");}
        if(precoUnitario.equals(BigDecimal.ZERO) || precoUnitario.compareTo(BigDecimal.ZERO) < 0){throw  new IllegalArgumentException("[X] Preco inválido para mercadoria");};
        if(categoriaDomain == null){throw new IllegalArgumentException("[X] O Produto DEVE pertencer a uma categoria");}

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.categoriaDomain = categoriaDomain;
    }

    public void atualizar(String novoNome, String novaDescricao, BigDecimal novoPrecoUnitario){
        if(novoNome == null || novoNome.isBlank()){throw new NomeInvalidoException("[X] Nome inválido ou vazio");}
        if(novaDescricao == null || novaDescricao.isBlank()){throw new NomeInvalidoException("[X] Descricao inválida ou vazia");}
        if(novoPrecoUnitario.equals(BigDecimal.ZERO) || novoPrecoUnitario.compareTo(BigDecimal.ZERO) < 0){throw  new IllegalArgumentException("[X] Preco inválido para mercadoria");}

        this.nome = novoNome;
        this.descricao = novaDescricao;
        this.precoUnitario = novoPrecoUnitario;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

}
