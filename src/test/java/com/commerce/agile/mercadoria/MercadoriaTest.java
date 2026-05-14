package com.commerce.agile.mercadoria;

import com.commerce.agile.dto.categoria.RequestCategoriaDTO;
import com.commerce.agile.dto.categoria.ResponseCategoriaDTO;
import com.commerce.agile.dto.mercadoria.RequestMercadoriaDTO;
import com.commerce.agile.dto.mercadoria.ResponseMercadoriaDTO;
import com.commerce.agile.seguranca.excecoes.DuplicidadeException;
import com.commerce.agile.seguranca.excecoes.NaoEncontradoException;
import com.commerce.agile.service.CategoriaService;
import com.commerce.agile.service.MercadoriaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import src.main.java.com.commerce.agile.seguranca.excecoes.NomeInvalidoException;
import src.main.java.com.commerce.agile.seguranca.excecoes.NomeInvalidoException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MercadoriaTest {

    @Autowired
    private MercadoriaService mercadoriaService;

    @Autowired
    private CategoriaService categoriaService;

        @Nested
        class CorrectProcedureTest {

            ResponseCategoriaDTO responseCategoriaDTO = criarNovaCategoria();

            @Test
            void mercadoriaDeveSerCriada() {

                ResponseCategoriaDTO categoria = categoriaService.buscarCategoriaPorId(responseCategoriaDTO.id())
                        .orElseThrow(() -> new NaoEncontradoException("Categoria não encontrada"));

                RequestMercadoriaDTO request = new RequestMercadoriaDTO("Fogão", "Fogão de 4 bocas", BigDecimal.valueOf(275.04));

                ResponseMercadoriaDTO mercadoria = mercadoriaService.criarNovaMercadoria(request, categoria.id());

                assertEquals(request.nome(), mercadoria.nome());
                assertTrue(mercadoriaService.buscarMercadoriaPeloId(mercadoria.id()).isPresent());

            }

            @Test
            void mercadoriaDeveSerExcluida() {
                ResponseCategoriaDTO categoriaDTO = criarNovaCategoria();
                ResponseMercadoriaDTO mercadoriaDTO = criarNovaMercadoria(categoriaDTO);

                assertTrue(mercadoriaService.buscarMercadoriaPeloId(mercadoriaDTO.id()).isPresent());

                mercadoriaService.excluirMercadoriaPeloId(mercadoriaDTO.id());

                assertThrows(NaoEncontradoException.class, () -> {

                    mercadoriaService.buscarMercadoriaPeloId(mercadoriaDTO.id());

                });

            }

            @Test
            void MercadoriaDeveSerEditada() {
                //criando categoria muito genérica
                RequestCategoriaDTO requestCategoriaDTO = new RequestCategoriaDTO("Casa");
                ResponseCategoriaDTO categoriaCasa = categoriaService.criarNovaCategoria(requestCategoriaDTO);

                //mercadoria antiga com valores errados
                RequestMercadoriaDTO mercadoriaDTO = new RequestMercadoriaDTO("Fugao", "Fogão 4 bocs", BigDecimal.valueOf(275.04));

                ResponseMercadoriaDTO mercadoriaCriada = mercadoriaService.criarNovaMercadoria(mercadoriaDTO, categoriaCasa.id());

                //criando categoria mais específica
                RequestCategoriaDTO novaCategoriaDTO = new RequestCategoriaDTO("Cozinha");
                ResponseCategoriaDTO categoriaCozinha = categoriaService.criarNovaCategoria(novaCategoriaDTO);

                //mercadoria nova
                RequestMercadoriaDTO novaMercadoria = new RequestMercadoriaDTO("Fogão", "Fogão 4 bocas", BigDecimal.valueOf(280.05));

                ResponseMercadoriaDTO mercadoriaEditada = mercadoriaService.editarDadosMercadoria(mercadoriaCriada.id(), novaMercadoria, categoriaCozinha.id());

                assertNotEquals(mercadoriaCriada.nome(), mercadoriaEditada.nome());
                assertNotEquals(mercadoriaCriada.descricao(), mercadoriaEditada.descricao());
                assertNotEquals(mercadoriaCriada.precoUnitario(), mercadoriaEditada.precoUnitario());
                assertNotEquals(mercadoriaCriada.categoria().getId(), mercadoriaEditada.categoria().getId());

                //verificar se outra entidade foi criada
                Long mercadoriaAntigaId = mercadoriaCriada.id();
                Long mercadoriaNovaId = mercadoriaEditada.id();

                assertEquals(mercadoriaAntigaId, mercadoriaNovaId);

            }

            //helper
            private ResponseCategoriaDTO criarNovaCategoria() {

                RequestCategoriaDTO dto = new RequestCategoriaDTO("Cozinha");
                return categoriaService.criarNovaCategoria(dto);

            }

            private ResponseMercadoriaDTO criarNovaMercadoria(ResponseCategoriaDTO categoriaDTO) {

                RequestMercadoriaDTO mercadoriaDTO = new RequestMercadoriaDTO("Fogão", "Fogão 4 bocas",
                        BigDecimal.valueOf(275.04));

                return mercadoriaService.criarNovaMercadoria(mercadoriaDTO, categoriaDTO.id());

            }
        }

        @Nested
        class FailProcedureTest{

            @Test
            @DisplayName("Mercadoria não pode ser criada sem uma categoria associada")
            void mercadoriaNaoPodeSerCriadaSemCategoria(){
                RequestMercadoriaDTO mercadoriaDTO = new RequestMercadoriaDTO("Cadeira de madeira", "Uma cadeira de madeira", BigDecimal.valueOf(124));


                assertThrows(NaoEncontradoException.class, () -> {

                    mercadoriaService.criarNovaMercadoria(mercadoriaDTO, 0L);

                });

            }

            @Test
            @DisplayName("A mercadoria não pode ser criada sem os parâmetros necessários")
            void mercadoriaNaoPodeSerCriadaSemAtributos(){

                RequestMercadoriaDTO mercadoriaDTO = new RequestMercadoriaDTO("", "cadeira", BigDecimal.TEN);

                RequestCategoriaDTO categoriaDTO = new RequestCategoriaDTO("Cozinha");
                ResponseCategoriaDTO cozinha = categoriaService.criarNovaCategoria(categoriaDTO);


                assertThrows(NomeInvalidoException.class, () ->{

                    mercadoriaService.criarNovaMercadoria(mercadoriaDTO, cozinha.id());

                });

            }

            @Test
            @DisplayName("Não se pode haver mercadoria duplicada")
            void naoPodeHaverMarcadoriaDuplicada(){

                RequestCategoriaDTO categoriaDTO = new RequestCategoriaDTO("Cozinha");
                ResponseCategoriaDTO cozinha = categoriaService.criarNovaCategoria(categoriaDTO);

                criarMercadoria("Cadeira", "Uma cadeira", cozinha);

                assertThrows(DuplicidadeException.class, () ->{

                    criarMercadoria("Cadeira", "Uma cadeira", cozinha);

                });

            }

            @Test
            @DisplayName("Mercadoria não pode ser criada com nome inválido")
            void mercadoriaNaoPodeSerCriadaComNomeInvalido(){
                RequestCategoriaDTO categoriaDTO = new RequestCategoriaDTO("Cozinha");
                ResponseCategoriaDTO cozinha = categoriaService.criarNovaCategoria(categoriaDTO);

                assertThrows(NomeInvalidoException.class, () ->{

                    criarMercadoria("cadeira1", "Uma cadeira", cozinha);

                });

                assertThrows(NomeInvalidoException.class, () ->{

                    criarMercadoria("1cadeira", "Uma cadeira", cozinha);

                });

                assertThrows(NomeInvalidoException.class, () ->{

                    criarMercadoria("cad31ra", "Uma cadeira", cozinha);

                });

            }

        }

        //helper
    ResponseMercadoriaDTO criarMercadoria(String nome, String descricao, ResponseCategoriaDTO categoria){
            RequestMercadoriaDTO mercadoriaDTO = new RequestMercadoriaDTO(nome, descricao, BigDecimal.TEN);

            return mercadoriaService.criarNovaMercadoria(mercadoriaDTO, categoria.id());
    }
}
