package com.commerce.agile.dto.mercadoria;

import com.commerce.agile.dominio.CategoriaDomain;
import com.commerce.agile.entidade.Categoria;

import java.math.BigDecimal;

public record RequestMercadoriaDTO(
        String nome,
        String descricao,
        BigDecimal precoUnitario
){}

