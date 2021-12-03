package br.com.fabiofnc.apileilao.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Proposta {

    private BigDecimal valor;
    private String Autor;
    private String mensagem;
    private final LocalDateTime dataDaProposta = LocalDateTime.now();
    private Produto produto;

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
