package com.commerce.agile.repository;

import com.commerce.agile.dominio.CategoriaDomain;
import com.commerce.agile.entidade.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findById(Long id);
    boolean existsByNome(String nome);
}
