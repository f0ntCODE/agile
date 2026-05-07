package com.commerce.agile.dto.mercadoria;

import com.commerce.agile.entidade.Categoria;

import java.math.BigDecimal;

public record ResponseMercadoriaDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal precoUnitario,
        Categoria categoria
)
{}
