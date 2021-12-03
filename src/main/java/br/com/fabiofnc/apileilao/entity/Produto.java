package br.com.fabiofnc.apileilao.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Produto {

    private String nome;
    private BigDecimal valorInicial;
    private NegociaçaoDoProduto negociaçaoDoProduto = NegociaçaoDoProduto.ABERTO;
    private final LocalDate dataDeLeilao = LocalDate.now();
    private List<Proposta> propostas = new ArrayList<Proposta>();
    private Usuario donoDoProduto;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    public NegociaçaoDoProduto getNegociaçaoDoProduto() {
        return negociaçaoDoProduto;
    }

    public void setNegociaçaoDoProduto(NegociaçaoDoProduto negociaçaoDoProduto) {
        this.negociaçaoDoProduto = negociaçaoDoProduto;
    }

    public List<Proposta> getPropostas() {
        return propostas;
    }

    public void setPropostas(List<Proposta> propostas) {
        this.propostas = propostas;
    }

    public LocalDate getDataDeLeilao() {
        return dataDeLeilao;
    }

    public Usuario getDonoDoProduto() {
        return donoDoProduto;
    }

    public void setDonoDoProduto(Usuario donoDoProduto) {
        this.donoDoProduto = donoDoProduto;
    }
}
