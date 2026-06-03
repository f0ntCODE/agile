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

        Optional<ProdutosCarrinho> produtoExistente = buscarProduto(mercadoria);

        if(produtoExistente.isPresent()) {

            produtoExistente.get().somarProduto(quantidade);

        }

        else{

            produtos.add(new ProdutosCarrinho(mercadoria, quantidade));

        }

    }

    public void removerDoCarrinho(MercadoriaDomain mercadoria, short quantidade) {

        if(quantidade <= 0){throw new IllegalArgumentException("Quantidade inválida");}

        Optional<ProdutosCarrinho> produtoExistente = buscarProduto(mercadoria);

        if(produtoExistente.isEmpty()) {throw new NaoEncontradoException("A mercadoria não foi encontrada");}

        if(produtoExistente.get().getQuantidade() < quantidade){
            throw new IllegalArgumentException("Não se pode remover além da quantidade da mercadoria que existe do carrinho");

        }

        if (produtoExistente.get().getQuantidade() == quantidade) {

            produtos.remove(produtoExistente.get());

            return;

        }

        produtoExistente.get().subtrairProduto(quantidade);

    }


    /**
     *Buscar o produto da classe {@link ProdutosCarrinho} e retornar o objeto {@link MercadoriaDomain}.
     * @param mercadoria
     * @return objeto {@link Optional} da classe {@link MercadoriaDomain}
     * @throws NaoEncontradoException
     */
    private Optional<ProdutosCarrinho> buscarProduto(MercadoriaDomain mercadoria){

        return produtos.stream().filter(p ->
                p.getMercadoria().equals(mercadoria)).findFirst();

    }

}
