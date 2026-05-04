package com.commerce.agile.entidade;

import com.commerce.agile.dominio.CategoriaDomain;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
public class Mercadoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "O nome não pode estar vazio")
    private String nome;

    @NotBlank(message = "Descrição não pode estar vazia")
    @Size(min = 10)
    private String descricao;

    @NotBlank(message = "Preço não pode estar vazio")
    private BigDecimal precoUnitario;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public Categoria getCategoria() {
        return categoria;
    }


}
