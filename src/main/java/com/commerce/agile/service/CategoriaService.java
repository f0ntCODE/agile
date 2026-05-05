package com.commerce.agile.service;

import com.commerce.agile.dominio.CategoriaDomain;
import com.commerce.agile.dto.categoria.CategoriaDTO;
import com.commerce.agile.entidade.Categoria;
import com.commerce.agile.mapper.mercadoria.CategoriaMapper;
import com.commerce.agile.repository.CategoriaRepository;
import com.commerce.agile.seguranca.excecoes.NaoEncontradoException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaMapper categoriaMapper;

    @Transactional
    public CategoriaDTO criarNovaCategoria(CategoriaDTO categoriaDTO){
        CategoriaDomain categoriaDomain = new CategoriaDomain(
                categoriaDTO.id(),
                categoriaDTO.nome()
        );

        Categoria entidade = categoriaMapper.toEntity(categoriaDomain);

        Categoria salva = categoriaRepository.save(entidade);

        CategoriaDomain categoriaSalva = categoriaMapper.toDomain(salva);

        return  categoriaMapper.toDto(categoriaSalva);
    }

    @Transactional
    public Optional<CategoriaDTO> buscarCategoriaPorId(Long id){

        Optional<Categoria> categoriaEncontrada = categoriaRepository.findById(id);
        CategoriaDomain categoria = categoriaMapper.toDomain(categoriaEncontrada.get());

        return Optional.of(categoriaMapper.toDto(categoria));
    }
}
