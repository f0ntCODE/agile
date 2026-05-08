package com.commerce.agile.controller;

import com.commerce.agile.dto.categoria.RequestCategoriaDTO;
import com.commerce.agile.dto.categoria.ResponseCategoriaDTO;
import com.commerce.agile.service.CategoriaService;
import com.commerce.agile.service.MercadoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private MercadoriaService mercadoriaService;

    @PostMapping("/criar")
    public ResponseEntity<ResponseCategoriaDTO> criarNovaCategoria(
            @RequestBody @Valid RequestCategoriaDTO categoriaDTO
            ){

        ResponseCategoriaDTO categoria = categoriaService.criarNovaCategoria(categoriaDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @PutMapping("/editar/{categoriaId}")
    public ResponseEntity<ResponseCategoriaDTO> editarDadosMercadoria(
            @PathVariable Long categoriaId,
            @RequestBody RequestCategoriaDTO request
    ){
        ResponseCategoriaDTO responseCategoriaDTO = categoriaService.editarDadosCategoria(categoriaId, request);

        return ResponseEntity.status(HttpStatus.OK).body(responseCategoriaDTO);

    }

    @DeleteMapping("/deletar/{categoriaId}")
    public ResponseEntity<Void> excluirCategoria(@PathVariable Long categoriaId){

        categoriaService.excluirCategoriaPeloId(categoriaId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ResponseCategoriaDTO>> listarTudo(){
        List<ResponseCategoriaDTO> listaCategorias = categoriaService.listarTodas();

        return ResponseEntity.ok().body(listaCategorias);

    }
}
