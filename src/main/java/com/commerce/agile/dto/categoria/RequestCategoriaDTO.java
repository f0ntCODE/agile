package com.commerce.agile.dto.categoria;

import jakarta.validation.constraints.NotBlank;

public record RequestCategoriaDTO(@NotBlank String nome)
{}
