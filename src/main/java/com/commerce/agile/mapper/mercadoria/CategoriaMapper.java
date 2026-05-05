package com.commerce.agile.mapper.mercadoria;

import com.commerce.agile.dominio.CategoriaDomain;
import com.commerce.agile.dto.categoria.CategoriaDTO;
import com.commerce.agile.entidade.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaDomain toDomain(Categoria entidade);

    @Mapping(target = "id", ignore = true)
    Categoria toEntity(CategoriaDomain dominio);

    CategoriaDomain toDomainFromDto(CategoriaDTO dto);

    CategoriaDTO toDto(CategoriaDomain dominio);
}
