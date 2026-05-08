package com.commerce.agile.categoria;

import com.commerce.agile.dto.categoria.RequestCategoriaDTO;
import com.commerce.agile.dto.categoria.ResponseCategoriaDTO;
import com.commerce.agile.seguranca.excecoes.NaoEncontradoException;
import com.commerce.agile.service.CategoriaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        @DisplayName("Deve-se obter a lista de todas as categorias registrada")
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

    }

    //helper
    private ResponseCategoriaDTO criarCategoria(String nomeCategoria) {
        RequestCategoriaDTO categoriaDTO = new RequestCategoriaDTO(nomeCategoria);

        return categoriaService.criarNovaCategoria(categoriaDTO);
    }
}
