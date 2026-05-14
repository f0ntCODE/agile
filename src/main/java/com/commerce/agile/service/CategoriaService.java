package com.commerce.agile.service;

import com.commerce.agile.dominio.CategoriaDomain;
import com.commerce.agile.dto.categoria.RequestCategoriaDTO;
import com.commerce.agile.dto.categoria.ResponseCategoriaDTO;
import com.commerce.agile.entidade.Categoria;
import com.commerce.agile.entidade.Mercadoria;
import com.commerce.agile.mapper.mercadoria.CategoriaMapper;
import com.commerce.agile.repository.CategoriaRepository;
import com.commerce.agile.seguranca.excecoes.DuplicidadeException;
import com.commerce.agile.seguranca.excecoes.NaoEncontradoException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaMapper categoriaMapper;

    @Transactional
    public ResponseCategoriaDTO criarNovaCategoria(RequestCategoriaDTO requestCategoriaDTO){

        if(categoriaRepository.existsByNome(requestCategoriaDTO.nome())){throw new DuplicidadeException("Não é permitido categorias duplicadas");
        }

        CategoriaDomain categoriaDomain = new CategoriaDomain(
                requestCategoriaDTO.nome()
        );

        Categoria entidade = categoriaMapper.toEntity(categoriaDomain);

        Categoria salva = categoriaRepository.save(entidade);

        CategoriaDomain categoriaSalva = categoriaMapper.toDomain(salva);

        return  categoriaMapper.toDto(categoriaSalva);
    }

    @Transactional
    public void excluirCategoriaPeloId(Long idCategoria){
        if(categoriaRepository.findById(idCategoria).isEmpty()){
            throw new NaoEncontradoException("Não foi possível excluir a categoria: Categoria não encontrada");}

        categoriaRepository.deleteById(idCategoria);

    }

    @Transactional
    public ResponseCategoriaDTO editarDadosCategoria(Long idCategoria, RequestCategoriaDTO categoriaDTO){

        Categoria categoriaEncontrada = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new NaoEncontradoException("Categoria não encontrada"));

        categoriaEncontrada.setNome(categoriaDTO.nome());

        Categoria categoriaAtualizada = categoriaRepository.save(categoriaEncontrada);

        return categoriaMapper.toResponseFromEntity(categoriaAtualizada);

    }

    @Transactional
    public Optional<ResponseCategoriaDTO> buscarCategoriaPorId(Long id){

        Optional<Categoria> categoriaEncontrada = Optional.of(categoriaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Categoria não encontrada")));

        CategoriaDomain categoria = categoriaMapper.toDomain(categoriaEncontrada.get());

        return Optional.of(categoriaMapper.toDto(categoria));
    }

    public List<ResponseCategoriaDTO> listarTodas(){

        List<Categoria> categoriaDTOList = categoriaRepository.findAll();

        return categoriaDTOList
                .stream()
                .map(categoriaMapper::toResponseFromEntity)
                .toList();

    }
}
