package com.commerce.agile.categoria;

import com.commerce.agile.dto.categoria.RequestCategoriaDTO;
import com.commerce.agile.dto.categoria.ResponseCategoriaDTO;
import com.commerce.agile.seguranca.excecoes.DuplicidadeException;
import com.commerce.agile.seguranca.excecoes.NaoEncontradoException;
import com.commerce.agile.service.CategoriaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import src.main.java.com.commerce.agile.seguranca.excecoes.NomeInvalidoException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoriaTest {

    @Autowired
    private CategoriaService categoriaService;

    @Nested
    class CorrectProcedureTest {

        @Test
        @DisplayName("Categoria deve ser criada")
        void deveCriarMercadoria() {

            RequestCategoriaDTO categoriaDTO = new RequestCategoriaDTO("Cozinha");

            ResponseCategoriaDTO response = categoriaService.criarNovaCategoria(categoriaDTO);

            assertTrue(categoriaService.buscarCategoriaPorId(response.id()).isPresent());
            assertEquals(categoriaDTO.nome(), response.nome());

        }

        @Test
        @DisplayName("Categoria deve ser excluída")
        void categoriaDeveSerExcluida() {
            ResponseCategoriaDTO categoriaDTO = criarCategoria("Cozinha");

            categoriaService.excluirCategoriaPeloId(categoriaDTO.id());

            assertThrows(NaoEncontradoException.class, () -> {
                categoriaService.buscarCategoriaPorId(categoriaDTO.id());
            });

        }

        @Test
        @DisplayName("Categoria deve ser editada")
        void mercadoriaDeveSerEditada() {
            ResponseCategoriaDTO categoriaDTO = criarCategoria("Cozinha");

            RequestCategoriaDTO novoDado = new RequestCategoriaDTO("Sala de estar");

            ResponseCategoriaDTO categoriaEditada = categoriaService.editarDadosCategoria(categoriaDTO.id(), novoDado);

            assertNotEquals(categoriaDTO.nome(), categoriaEditada.nome());
            assertEquals(categoriaDTO.id(), categoriaEditada.id());

        }

        @Test
        @DisplayName("Deve-se obter a lista de todas as categorias registradas")
        void deveResgatarTodasAsCategoriasResgistradas(){
            ResponseCategoriaDTO categoriaCozinhaDto = criarCategoria("Cozinha");
            ResponseCategoriaDTO categoriaQuartoDto = criarCategoria("Quarto");
            ResponseCategoriaDTO categoriaBanheiroDto = criarCategoria("Banheiro");

            List<ResponseCategoriaDTO> listaCategorias = categoriaService.listarTodas();
            List<ResponseCategoriaDTO> listaCategoriasCriadas = new ArrayList<>();

            listaCategoriasCriadas.add(categoriaCozinhaDto);
            listaCategoriasCriadas.add(categoriaQuartoDto);
            listaCategoriasCriadas.add(categoriaBanheiroDto);

            assertEquals(3, listaCategorias.size());
            assertArrayEquals(listaCategoriasCriadas.toArray(), listaCategorias.toArray());

        }

        @Nested
        class FailProcedureTest{

            @Test
            @DisplayName("Não pode haver categorias duplicadas")
            void naoPodeHaverCategoriasDuplicadas(){
                ResponseCategoriaDTO cozinha = criarCategoria("Cozinha");

                //criar uma nova categoria, duplicada
                RequestCategoriaDTO categoriaDTO = new RequestCategoriaDTO("Cozinha");

                assertThrows(DuplicidadeException.class, () -> {
                    ResponseCategoriaDTO cozinha2 = categoriaService.criarNovaCategoria(categoriaDTO);

                });

            }

            @Test
            @DisplayName("Categoria sem nome não pode ser criada")
            void categoriaSemNomeNaoPodeSerCriada(){
                RequestCategoriaDTO categoriaDTO = new RequestCategoriaDTO("");

                assertThrows(NomeInvalidoException.class, () ->{

                    categoriaService.criarNovaCategoria(categoriaDTO);

                });

            }

            @Test
            @DisplayName("Categoria com nome inválido não pode ser criada")
            void categoriaComNomeInvalidoNaoPodeSerCriada(){
                RequestCategoriaDTO categoriaDTO = new RequestCategoriaDTO("c0zinh4");

                assertThrows(NomeInvalidoException.class, () ->{

                    categoriaService.criarNovaCategoria(categoriaDTO);

                });

            }

        }

    }

    //helper
    private ResponseCategoriaDTO criarCategoria(String nomeCategoria) {
        RequestCategoriaDTO categoriaDTO = new RequestCategoriaDTO(nomeCategoria);

        return categoriaService.criarNovaCategoria(categoriaDTO);
    }
}
