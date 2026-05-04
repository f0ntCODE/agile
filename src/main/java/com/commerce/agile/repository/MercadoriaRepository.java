package com.commerce.agile.repository;

import com.commerce.agile.dominio.MercadoriaDomain;
import com.commerce.agile.entidade.Mercadoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MercadoriaRepository extends JpaRepository<Mercadoria, Long> {
    Optional<Mercadoria> findById(Long id);

}
