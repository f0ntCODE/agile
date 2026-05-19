package com.commerce.agile.dominio;

import com.commerce.agile.seguranca.excecoes.EmaiIInvalidoException;
import src.main.java.com.commerce.agile.seguranca.excecoes.NomeInvalidoException;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClienteDomain {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private LocalDateTime dataNascimento;
    private String CPF;
    private String role;

    //para JPA
    public ClienteDomain() {}

    public ClienteDomain(String nome, String email, String senha, LocalDateTime dataNascimento, String CPF) {

        Pattern padraoEmail = Pattern.compile(".*@email.com");
        Matcher matcher = padraoEmail.matcher(email);

        if(nome == null || nome.trim().equals("")) { throw new NomeInvalidoException("Nome de usuário está inválido");}
        if(!matcher.hasMatch()){throw new EmaiIInvalidoException("Email está inválido");}
        if(){}

        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.CPF = CPF;
    }
}
