package com.commerce.agile.controller.rest;

import com.commerce.agile.dto.mercadoria.ResponseMercadoriaDTO;
import com.commerce.agile.service.MercadoriaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mercadoria")
public class MercadoriaController {

    @Autowired
    private MercadoriaService mercadoriaService;

    @GetMapping
    public String upMainPage(Model model){

        List<ResponseMercadoriaDTO> mercadorias = mercadoriaService.listarTodas();

        model.addAttribute("mercadorias", mercadorias);

        return "ProdutoList";
    }



}
