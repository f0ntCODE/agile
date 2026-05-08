package com.commerce.agile.controller;

import com.commerce.agile.dto.mercadoria.RequestMercadoriaDTO;
import com.commerce.agile.dto.mercadoria.ResponseMercadoriaDTO;
import com.commerce.agile.service.MercadoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mercadoria")
public class MercadoriaController {

    @Autowired
    private MercadoriaService mercadoriaService;

    @PostMapping("/criar/{categoriaId}")
    public ResponseEntity<ResponseMercadoriaDTO> criarNovaMercadoria(
            @RequestBody RequestMercadoriaDTO request,
            @PathVariable Long categoriaId
            ){

        ResponseMercadoriaDTO mercadoriaDTO = mercadoriaService.criarNovaMercadoria(request, categoriaId);

        return ResponseEntity.status(HttpStatus.CREATED).body(mercadoriaDTO);
    }

    @PutMapping("/editar/{mercadoriaId}/{categoriaId}")
    public ResponseEntity<ResponseMercadoriaDTO> editarMercadoria(
            @RequestBody RequestMercadoriaDTO novosDados,
            @PathVariable Long mercadoriaId,
            @PathVariable Long categoriaId){

        ResponseMercadoriaDTO response = mercadoriaService.editarDadosMercadoria(mercadoriaId, novosDados, categoriaId);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @DeleteMapping("/excluir/{mercadoriaId}")
    public ResponseEntity<Void> removerMercadoria(@PathVariable Long mercadoriaId){

        mercadoriaService.excluirMercadoriaPeloId(mercadoriaId);

        return ResponseEntity.noContent().build();

    }

    @GetMapping
    public ResponseEntity<List<ResponseMercadoriaDTO>> listarTodas(){
        List<ResponseMercadoriaDTO> mercadoriaList =
                mercadoriaService.listarTodas();

        return ResponseEntity.ok(mercadoriaList);
    }
}
