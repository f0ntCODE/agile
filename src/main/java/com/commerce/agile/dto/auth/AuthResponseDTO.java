package com.commerce.agile.dto.auth;

import com.commerce.agile.dto.cliente.ResponseClienteDTO;

public record AuthResponseDTO(
        String token,
        String tipo,
        ResponseClienteDTO cliente
) {
}
