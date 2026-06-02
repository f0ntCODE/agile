package com.commerce.agile.dominio;

import com.commerce.agile.infraestrutura.seguranca.excecoes.NaoEncontradoException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CarrinhoDomain {

    private ClienteDomain cliente;
    private Set<ProdutosCarrinho> produtos = new HashSet<>();

    public CarrinhoDomain() {}

    public CarrinhoDomain(ClienteDomain cliente) {
        if(cliente == null) { throw new IllegalArgumentException("ID inválido");}

        this.cliente = cliente;

    }

    public void adicionarAoCarrinho(MercadoriaDomain mercadoria, short quantidade) {

        if(quantidade <= 0) throw new IllegalArgumentException("Quantidade inválida");

        Optional<ProdutosCarrinho> produtoExistente = produtos.stream().filter(p ->
                p.getMercadoria().equals(mercadoria)).findFirst();

        if(produtoExistente.isPresent()) {

            produtoExistente.get().somarProduto(quantidade);

        }

        else{

            produtos.add(new ProdutosCarrinho(mercadoria, quantidade));

        }

    }

}
