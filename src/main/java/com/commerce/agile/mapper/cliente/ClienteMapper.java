package com.commerce.agile.mapper.cliente;

import com.commerce.agile.dominio.ClienteDomain;
import com.commerce.agile.dto.cliente.ResponseClienteDTO;
import com.commerce.agile.entidade.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface ClienteMapper {

    ResponseClienteDTO toDTOFromDomain(ClienteDomain domain);

    ClienteDomain toDomainFromDTO(ResponseClienteDTO dto);

    @Mapping( target = "id", ignore = true)
    Cliente toEntityFromDomain(ClienteDomain domain);

    ClienteDomain toDomainFromEntity(Cliente cliente);

    ResponseClienteDTO toDTOFromEntity(Cliente cliente);

}
