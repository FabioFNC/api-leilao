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
    private String descricao;
    @Enumerated(EnumType.STRING)
    private NegociaçaoDoProduto negociaçaoDoProduto = NegociaçaoDoProduto.ABERTO;
    private final LocalDate dataDeLeilao = LocalDate.now();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Proposta> propostas = new ArrayList<Proposta>();
    @ManyToOne
    private Usuario donoDoProduto;

    public Produto() {}

    public Produto(String nome, BigDecimal valorInicial, Usuario donoDoProduto) {
        this.nome = nome;
        this.valorInicial = valorInicial;
        this.donoDoProduto = donoDoProduto;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
