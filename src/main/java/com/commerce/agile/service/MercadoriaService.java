package com.commerce.agile.service;

import com.commerce.agile.dominio.MercadoriaDomain;
import com.commerce.agile.dto.mercadoria.RequestMercadoriaDTO;
import com.commerce.agile.dto.mercadoria.ResponseMercadoriaDTO;
import com.commerce.agile.entidade.Categoria;
import com.commerce.agile.entidade.Mercadoria;
import com.commerce.agile.mapper.mercadoria.CategoriaMapper;
import com.commerce.agile.mapper.mercadoria.MercadoriaMapper;
import com.commerce.agile.repository.CategoriaRepository;
import com.commerce.agile.repository.MercadoriaRepository;
import com.commerce.agile.seguranca.excecoes.DuplicidadeException;
import com.commerce.agile.seguranca.excecoes.NaoEncontradoException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MercadoriaService {

    @Autowired
    private MercadoriaMapper mercadoriaMapper;
    @Autowired
    private CategoriaMapper categoriaMapper;

    @Autowired
    private CategoriaRepository categoriaRepository;

    private final MercadoriaRepository mercadoriaRepository;

    private final CategoriaService categoriaService;


    public MercadoriaService(MercadoriaRepository mercadoriaRepository,
                             CategoriaService categoriaService) {
        this.mercadoriaRepository = mercadoriaRepository;
        this.categoriaService = categoriaService;
    }

    @Transactional
    public ResponseMercadoriaDTO criarNovaMercadoria(RequestMercadoriaDTO request, Long idCategoria){

        if(mercadoriaRepository.existsByNome(request.nome())){throw  new DuplicidadeException("Essa mercadoria já existe");
        }

        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new NaoEncontradoException("Categoria não encontrada"));


        MercadoriaDomain mercadoriaDomain = new MercadoriaDomain(
                request.nome(),
                request.descricao(),
                request.precoUnitario());

        Mercadoria mercadoria = mercadoriaMapper.toEntity(mercadoriaDomain);

        mercadoria.setCategoria(categoria);

        Mercadoria salva = mercadoriaRepository.save(mercadoria);

        MercadoriaDomain mercadoriaSalva = mercadoriaMapper.toDomain(salva);

        return mercadoriaMapper.toResponse(mercadoriaSalva);
    }

    @Transactional
    public void excluirMercadoriaPeloId(Long idMercadoria){
        if(mercadoriaRepository.findById(idMercadoria).isEmpty()){throw new NaoEncontradoException("Mercadoria não encontrada");}

        mercadoriaRepository.deleteById(idMercadoria);
    }

    @Transactional
    public ResponseMercadoriaDTO editarDadosMercadoria(Long idMercadoria, RequestMercadoriaDTO novosDados, Long idCategoria){

        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new NaoEncontradoException("Categoria não econtrada"));

        Mercadoria mercadoria = mercadoriaRepository.findById(idMercadoria)
                .orElseThrow(() -> new NaoEncontradoException("Mercadoria não encontrada"));

        mercadoria.setNome(novosDados.nome());
        mercadoria.setDescricao(novosDados.descricao());
        mercadoria.setPrecoUnitario(novosDados.precoUnitario());
        mercadoria.setCategoria(categoria);

        if(!idCategoria.equals(mercadoria.getCategoria().getId())){mercadoria.setCategoria(categoria);}

        Mercadoria mercadoriaSalva = mercadoriaRepository.save(mercadoria);

        return mercadoriaMapper.toResponseFromEntity(mercadoriaSalva);

    }


    @Transactional
    public Optional<ResponseMercadoriaDTO> buscarMercadoriaPeloId(Long id){

        Optional<Mercadoria> mercadoriaEncontrada = Optional.of(mercadoriaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Mercadoria não encontrada")));

        ResponseMercadoriaDTO response = mercadoriaMapper.toResponseFromEntity(mercadoriaEncontrada.get());

        return Optional.of(response);

    }

    public List<ResponseMercadoriaDTO> listarTodas(){
        List<Mercadoria> listaMercadorias =
                mercadoriaRepository.findAll();

        return listaMercadorias
                .stream()
                .map(mercadoriaMapper::toResponseFromEntity)
                .toList();

    }

}
