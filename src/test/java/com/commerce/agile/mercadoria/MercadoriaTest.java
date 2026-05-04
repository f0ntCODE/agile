package com.commerce.agile.mercadoria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.commerce.agile.dominio.CategoriaDomain;
import com.commerce.agile.dto.categoria.CategoriaDTO;
import com.commerce.agile.dto.mercadoria.RequestMercadoriaDTO;
import com.commerce.agile.dto.mercadoria.ResponseMercadoriaDTO;
import com.commerce.agile.seguranca.excecoes.NaoEncontradoException;
import com.commerce.agile.service.CategoriaService;
import com.commerce.agile.service.MercadoriaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class MercadoriaTest {

    @Autowired
    private MercadoriaService mercadoriaService;

    @Autowired
    private CategoriaService categoriaService;

    @Test
    void mercadoriaDeveSerCriada(){
        CategoriaDTO dto = new CategoriaDTO(null, "Cozinha");
        CategoriaDTO categoriaDto = categoriaService.criarNovaCategoria(dto);

        CategoriaDTO categoria = categoriaService.buscarCategoriaPorId(categoriaDto.id())
                .orElseThrow(() -> new NaoEncontradoException("Categoria não encontrada"));

        RequestMercadoriaDTO request = new RequestMercadoriaDTO("Fogão", "Fogão de 4 bocas", BigDecimal.valueOf(275.04));
        
        ResponseMercadoriaDTO mercadoria = mercadoriaService.criarNovaMercadoria(request, categoria.id());

        assertEquals(request.nome(), mercadoria.nome());
        assertTrue(mercadoriaService.findMercadoriaById(mercadoria.id()).isPresent());

    }

}
