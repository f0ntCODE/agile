package com.commerce.agile.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RequestClienteDTO(
        @NotBlank(message = "Nome não pode estar em branco")
        String nome,

        @NotBlank(message = "Email não pode estar em branco")
        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "Senha não pode estar em branco")
        String senha,

        @NotBlank(message = "CPF não pode estar em branco")
        @Size(min = 11, message = "CPF fora dos padrões")
        String cpf,

        @NotBlank(message = "Data de nascimento vazia")
        LocalDate dataNascimento)
{}
