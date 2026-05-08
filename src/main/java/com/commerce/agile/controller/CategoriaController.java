package com.commerce.agile.controller;

import com.commerce.agile.dto.categoria.RequestCategoriaDTO;
import com.commerce.agile.dto.categoria.ResponseCategoriaDTO;
import com.commerce.agile.dto.mercadoria.ResponseMercadoriaDTO;
import com.commerce.agile.service.CategoriaService;
import com.commerce.agile.service.MercadoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/criar")
    public ResponseEntity<ResponseCategoriaDTO> criarNovaCategoria(
            @RequestBody @Valid RequestCategoriaDTO categoriaDTO
            ){

        ResponseCategoriaDTO categoria = categoriaService.criarNovaCategoria(categoriaDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }
}
