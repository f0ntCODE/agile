package com.commerce.agile.service;

import com.commerce.agile.dominio.CarrinhoDomain;
import com.commerce.agile.dominio.MercadoriaDomain;
import com.commerce.agile.entidade.Mercadoria;
import com.commerce.agile.infraestrutura.seguranca.excecoes.NaoEncontradoException;
import com.commerce.agile.mapper.mercadoria.MercadoriaMapper;
import com.commerce.agile.repository.MercadoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoService {

    @Autowired
    private MercadoriaRepository mercadoriaRepository;

    @Autowired
    private MercadoriaMapper  mercadoriaMapper;

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

        return carrinho;
    }

}