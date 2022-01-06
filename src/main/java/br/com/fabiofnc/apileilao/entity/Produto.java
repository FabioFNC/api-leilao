package br.com.fabiofnc.apileilao.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private List<Proposta> propostas = new ArrayList<>();
    @ManyToOne
    private Usuario donoDoProduto;

    public Produto() {}

    public Produto(String nome, BigDecimal valorInicial, Usuario donoDoProduto) {
        this.nome = nome;
        this.valorInicial = valorInicial;
        this.donoDoProduto = donoDoProduto;
    }

    public Optional<BigDecimal> maiorProposta() {
        List<BigDecimal> valorDasPropostas = this.propostas.stream()
                .map(Proposta::getValor).collect(Collectors.toList());
        return valorDasPropostas.stream().max(Comparator.naturalOrder());
    }

    public Optional<BigDecimal> maiorProposta(Long idProduto, Long idUsuario) {
        List<BigDecimal> valorDasPropostas = this.propostas.stream()
                .filter(v -> v.getUsuario().getId().equals(idUsuario) && v.getProduto().getId().equals(idProduto))
                .map(Proposta::getValor).collect(Collectors.toList());
        return valorDasPropostas.stream().max(Comparator.naturalOrder());
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
