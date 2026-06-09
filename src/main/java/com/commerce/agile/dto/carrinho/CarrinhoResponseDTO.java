package com.commerce.agile.dto.carrinho;

import java.math.BigDecimal;
import java.util.List;

public record CarrinhoResponseDTO(
        long idCliente,
        List<ProdutosCarrinhoResponseDTO> produtos,
        BigDecimal total
) {}
