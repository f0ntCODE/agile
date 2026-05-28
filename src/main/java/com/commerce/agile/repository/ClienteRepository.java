package com.commerce.agile.repository;

import com.commerce.agile.entidade.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByNome(String nome);
    Optional<Cliente> findByEmail(String email);
    Cliente findByCPF(String cpf);

    boolean existsByNome(String nome);
}
