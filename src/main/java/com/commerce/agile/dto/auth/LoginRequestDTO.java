package com.commerce.agile.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank(message = "Email nao pode estar em branco")
        @Email(message = "Email invalido")
        String email,

        @NotBlank(message = "Senha nao pode estar em branco")
        String senha
) {
}
