package com.commerce.agile.repository;

import com.commerce.agile.entidade.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByNome(String nome);
    Cliente findByEmail(String email);
    Cliente findByCPF(String cpf);
}
