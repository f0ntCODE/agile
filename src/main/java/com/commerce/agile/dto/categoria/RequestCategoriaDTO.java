package com.commerce.agile.dto.categoria;

import jakarta.validation.constraints.NotBlank;

/*
* Classe de requisição para DTO categoria
*
* */

public record RequestCategoriaDTO(@NotBlank String nome)
{}
