package br.com.fabiofnc.apileilao.controller.dto;

import br.com.fabiofnc.apileilao.entity.Produto;
import br.com.fabiofnc.apileilao.entity.Proposta;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDto {

    private Long id;
    private String nome;
    private BigDecimal valorInicial;
    private String descricao;
    private String negociaçaoDoProduto;
    private LocalDate dataDeLeilao;
    private String nomeDoAnunciante;
    private List<Long> idDasPropostas;

    public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.valorInicial = produto.getValorInicial();
        this.descricao = produto.getDescricao();
        this.negociaçaoDoProduto = String.valueOf(produto.getNegociaçaoDoProduto());
        this.dataDeLeilao = produto.getDataDeLeilao();
        this.nomeDoAnunciante = produto.getDonoDoProduto().getNome();
        this.idDasPropostas = produto.getPropostas().stream().map(Proposta::getId).collect(Collectors.toList());
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public String getNegociaçaoDoProduto() {
        return negociaçaoDoProduto;
    }

    public LocalDate getDataDeLeilao() {
        return dataDeLeilao;
    }

    public String getNomeDoAnunciante() {
        return nomeDoAnunciante;
    }

    public List<Long> getIdDasPropostas() {
        return idDasPropostas;
    }

    public static List<ProdutoDto> converterTodos(List<Produto> produtos) {
        return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
    }

}
