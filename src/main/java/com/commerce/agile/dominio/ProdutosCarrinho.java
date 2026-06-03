package com.commerce.agile.dominio;

public class ProdutosCarrinho {

    private MercadoriaDomain mercadoria;
    private short quantidade;

    public ProdutosCarrinho(){}

    public ProdutosCarrinho(MercadoriaDomain mercadoria, short quantidade) {
        this.mercadoria = mercadoria;
        this.quantidade = quantidade;

    }

    public void somarProduto(short quantidade){

        this.quantidade += quantidade;

    }

    public void subtrairProduto(short quantidade){

        this.quantidade -= quantidade;

    }

    public short getQuantidade() {return quantidade;}

    public MercadoriaDomain getMercadoria() {return mercadoria;}
}
