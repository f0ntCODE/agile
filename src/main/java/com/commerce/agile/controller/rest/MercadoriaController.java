package com.commerce.agile.controller.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mercadoria")
public class MercadoriaController {

    @GetMapping
    public String upMainPage(){
        return "ProdutoList";
    }



}
