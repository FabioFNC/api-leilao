package br.com.fabiofnc.apileilao.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal valorInicial;
    @Enumerated(EnumType.STRING)
    private NegociaçaoDoProduto negociaçaoDoProduto = NegociaçaoDoProduto.ABERTO;
    private final LocalDate dataDeLeilao = LocalDate.now();
    @OneToMany(mappedBy = "produto")
    private List<Proposta> propostas = new ArrayList<Proposta>();
    @ManyToOne
    private Usuario donoDoProduto;

    public Produto(String nome, BigDecimal valorInicial, Usuario donoDoProduto) {
        this.nome = nome;
        this.valorInicial = valorInicial;
        this.donoDoProduto = donoDoProduto;
    }

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
