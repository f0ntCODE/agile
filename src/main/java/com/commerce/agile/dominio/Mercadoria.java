package src.main.java.com.commerce.agile.dominio;
import java.math.BigDecimal;
import src.main.java.com.commerce.agile.seguranca.excecoes.NomeInvalidoException;

public class Mercadoria {

    private String nome;
    private String descricao;
    private BigDecimal precoUnitario;

    public Mercadoria(String nome, String descricao, BigDecimal precoUnitario){

        if(nome == null || nome.isBlank()){throw new IllegalArgumentException("[X] Nome inválido ou vazio");}
        if(descricao == null || descricao.isBlank(){throw new IllegalArgumentException("[X] Descricao inválida ou vazia")};)

        if(precoUnitario.equals(BigDecimal.ZERO) || precoUnitario.compareTo(BigDecimal.ZERO) < 0){throw new IllegalArgumentException("[X] Valor para mercadoria inválido.");}

        this.nome = nome;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;

    }

}
