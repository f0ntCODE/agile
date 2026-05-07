package com.commerce.agile.mapper.mercadoria;

import com.commerce.agile.dominio.MercadoriaDomain;
import com.commerce.agile.dto.mercadoria.RequestMercadoriaDTO;
import com.commerce.agile.dto.mercadoria.ResponseMercadoriaDTO;
import com.commerce.agile.entidade.Mercadoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses =  {CategoriaMapper.class})
public interface MercadoriaMapper {

    RequestMercadoriaDTO toDto(ResponseMercadoriaDTO responseMercadoriaDTO);

    ResponseMercadoriaDTO toResponse(MercadoriaDomain mercadoriaDomain);

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "precoUnitario", source = "precoUnitario")
    @Mapping(target = "categoria", source = "categoria")
    MercadoriaDomain toDomain(Mercadoria mercadoria);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "precoUnitario", source = "precoUnitario")
    @Mapping(target = "categoria", source = "categoria")
    Mercadoria toEntity(MercadoriaDomain mercadoriaDomain);

    ResponseMercadoriaDTO toResponseFromEntity(Mercadoria mercadoria);

}
