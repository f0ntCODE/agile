package com.commerce.agile.mapper.mercadoria;

import com.commerce.agile.dominio.CategoriaDomain;
import com.commerce.agile.dto.categoria.ResponseCategoriaDTO;
import com.commerce.agile.entidade.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaDomain toDomain(Categoria entidade);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nome", source = "nome")
    Categoria toEntity(CategoriaDomain dominio);

    CategoriaDomain toDomainFromDto(ResponseCategoriaDTO dto);

    ResponseCategoriaDTO toDto(CategoriaDomain dominio);

    ResponseCategoriaDTO toResponseFromEntity(Categoria categoria);
}
