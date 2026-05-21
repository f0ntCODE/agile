package com.commerce.agile.dto.cliente;

public record ResponseClienteDTO(
        Long id,
        String nome,
        String email,
        String role
)
{}
