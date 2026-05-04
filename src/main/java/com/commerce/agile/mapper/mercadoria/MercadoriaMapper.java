package com.commerce.agile.mapper.mercadoria;

import com.commerce.agile.dominio.MercadoriaDomain;
import com.commerce.agile.dto.mercadoria.RequestMercadoriaDTO;
import com.commerce.agile.dto.mercadoria.ResponseMercadoriaDTO;
import com.commerce.agile.entidade.Mercadoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MercadoriaMapper {

    RequestMercadoriaDTO toDto(ResponseMercadoriaDTO responseMercadoriaDTO);

    ResponseMercadoriaDTO toResponse(MercadoriaDomain mercadoriaDomain);

    @Mapping(target = "id", ignore = true)
    MercadoriaDomain toDomain(Mercadoria mercadoria);

    Mercadoria toEntity(MercadoriaDomain mercadoriaDomain);

    ResponseMercadoriaDTO toResponseFromEntity(Mercadoria mercadoria);

}
