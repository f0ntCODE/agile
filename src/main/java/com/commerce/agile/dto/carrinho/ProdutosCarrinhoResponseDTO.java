package com.commerce.agile.dto.carrinho;

import java.math.BigDecimal;

public record ProdutosCarrinhoResponseDTO(
        long idMercadoria,
        String nomeMercadoria,
        BigDecimal precoUnitario,
        short quantidade,
        BigDecimal subTotal
) {}
