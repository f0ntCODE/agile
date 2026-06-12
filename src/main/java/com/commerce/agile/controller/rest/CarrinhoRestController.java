package com.commerce.agile.controller.rest;

import com.commerce.agile.dto.carrinho.CarrinhoRequestDTO;
import com.commerce.agile.dto.carrinho.CarrinhoResponseDTO;
import com.commerce.agile.dto.carrinho.ProdutosCarrinhoResponseDTO;
import com.commerce.agile.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrinho")
public class CarrinhoRestController {

	@Autowired
	private CarrinhoService carrinhoService;

	@PostMapping("/adicionar")
		public List<CarrinhoResponseDTO> adicionarItem(@RequestBody CarrinhoRequestDTO carrinhoRequestDTO) {

		return  null;
	}

	@PostMapping("/removerItem")
	public List<ProdutosCarrinhoResponseDTO> removerItem(@RequestBody CarrinhoRequestDTO carrinhoRequestDTO) {

		return null;
	}

	@PostMapping("/limparCarrinho")
	public List<ProdutosCarrinhoResponseDTO> limparCarrinho() {

		return null;
	}

	@GetMapping("/carregarCarrinho")
	public List<ProdutosCarrinhoResponseDTO> carregarCarrinho() {

		return null;
	}

}
