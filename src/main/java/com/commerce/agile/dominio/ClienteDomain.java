package com.commerce.agile.dominio;

import com.commerce.agile.infraestrutura.seguranca.excecoes.CpfInvalidoException;
import com.commerce.agile.infraestrutura.seguranca.excecoes.EmaiIInvalidoException;
import com.commerce.agile.infraestrutura.seguranca.excecoes.IdadeException;
import com.commerce.agile.infraestrutura.seguranca.excecoes.SenhaInvalidaException;
import com.commerce.agile.infraestrutura.seguranca.excecoes.NomeInvalidoException;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClienteDomain {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    private String CPF;
    private String role;

    //para JPA
    public ClienteDomain() {}

    public ClienteDomain(String nome, String email, String senha, LocalDate dataNascimento, String CPF) {

        if(nome == null || nome.trim().isEmpty()) {throw new NomeInvalidoException("Nome de usuário está inválido");}

        if(isEmailValido(email) == false){throw new EmaiIInvalidoException("Email está inválido");}

        if(senha == null || senha.trim().isEmpty()){throw new SenhaInvalidaException("A senha está inválida");}

        if(!isCPFValido(CPF)){throw new CpfInvalidoException("CPF está inválido");
        }

        if(!isIdadeValida(dataNascimento)){throw  new IdadeException("Cliente é menor de idade");}

        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.CPF = CPF;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {

        if(nome == null || nome.trim().isEmpty()) {throw new NomeInvalidoException("Nome de usuário está inválido");}

        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        if(!isEmailValido(email)){throw new EmaiIInvalidoException("Email está inválido");}

        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {

        if(!isIdadeValida(dataNascimento)){throw new IdadeException("Cliente é menor de idade");}

        this.dataNascimento = dataNascimento;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {

        if(isCPFValido(CPF)){throw new CpfInvalidoException("CPF está inválido");};

            this.CPF = CPF;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private boolean isIdadeValida(LocalDate dataNascimento) {

        int anoAtual = LocalDate.now().getYear();
        double mesAtual = (LocalDate.now().getMonthValue())* 0.1;

        double epocaAtual = anoAtual + mesAtual;

        int anoNascimento = dataNascimento.getYear();
        double mesNascimento = (dataNascimento.getMonthValue()) * 0.1;

        double epocaNascimento = anoNascimento + mesNascimento;

        double idade = (epocaAtual - epocaNascimento);

        return (idade >= 18);
    }

    private boolean isEmailValido(String email) {

        Pattern padraoEmail = Pattern.compile(".+@email\\.com$");
        Matcher matcher = padraoEmail.matcher(email);
        if(matcher.find()){

            return true;
        }

        return false;
    }

    private boolean isCPFValido(String CPF) {
        return CPF.length() == 11;
    }
}
