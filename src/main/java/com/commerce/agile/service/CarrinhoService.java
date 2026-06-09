package com.commerce.agile.service;

import com.commerce.agile.dominio.CarrinhoDomain;
import com.commerce.agile.dominio.MercadoriaDomain;
import com.commerce.agile.dominio.ProdutosCarrinho;
import com.commerce.agile.dto.carrinho.ProdutosCarrinhoResponseDTO;
import com.commerce.agile.entidade.Mercadoria;
import com.commerce.agile.infraestrutura.seguranca.excecoes.NaoEncontradoException;
import com.commerce.agile.mapper.mercadoria.MercadoriaMapper;
import com.commerce.agile.repository.MercadoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarrinhoService {

    @Autowired
    private MercadoriaRepository mercadoriaRepository;

    @Autowired
    private MercadoriaMapper  mercadoriaMapper;

    private final Map<Long, CarrinhoDomain> carrinhosPorCliente = new HashMap<>();

    public CarrinhoDomain adicionarMercadoriaAoCarrinho(
            CarrinhoDomain carrinho,
            long idMercadoria,
            short quantidade) {

        Mercadoria mercadoria = mercadoriaRepository
                .findById(idMercadoria)
                .orElseThrow(() ->
                        new NaoEncontradoException("Mercadoria não encontrada. ID: " + idMercadoria));

        MercadoriaDomain mercadoriaDomain = mercadoriaMapper.toDomain(mercadoria);

        carrinho.adicionarAoCarrinho(mercadoriaDomain, quantidade);

        salvarCarrinhoDoCliente(carrinho);

        return carrinho;
    }

    public CarrinhoDomain removerMercadoriaDoCarrinho(
            long idMercadoria,
            short quantidade,
            CarrinhoDomain carrinho) {

        Mercadoria mercadoria = mercadoriaRepository
                .findById(idMercadoria)
                .orElseThrow(() ->
                        new NaoEncontradoException("Mercadoria não encontrada. ID: " + idMercadoria));

        MercadoriaDomain mercadoriaDomain = mercadoriaMapper.toDomain(mercadoria);

        carrinho.removerDoCarrinho(mercadoriaDomain, quantidade);

        salvarCarrinhoDoCliente(carrinho);

        return carrinho;
    }

    public List<ProdutosCarrinhoResponseDTO> carregarCarrinho(long idCliente) {

        CarrinhoDomain carrinho = carrinhosPorCliente.get(idCliente);

        if(carrinho == null) {
            System.out.println("Carrinho vazio");
            return List.of();
        }

        return carrinho.getProdutos()
                .stream()
                .map(this::toProdutosCarrinhoResponseDTO)
                .toList();
    }

    private ProdutosCarrinhoResponseDTO toProdutosCarrinhoResponseDTO(ProdutosCarrinho produto){

        MercadoriaDomain mercadoria = produto.getMercadoria();
        BigDecimal subTotal = mercadoria.getPrecoUnitario()
                .multiply(BigDecimal.valueOf(produto.getQuantidade()));

        return new ProdutosCarrinhoResponseDTO(
                mercadoria.getId(),
                mercadoria.getNome(),
                mercadoria.getPrecoUnitario(),
                produto.getQuantidade(),
                subTotal
        );
    }

    private void salvarCarrinhoDoCliente(CarrinhoDomain carrinho) {

        if(carrinho.getCliente() == null || carrinho.getCliente().getId() == null) {
            return;
        }

        carrinhosPorCliente.put(carrinho.getCliente().getId(), carrinho);
    }

}
