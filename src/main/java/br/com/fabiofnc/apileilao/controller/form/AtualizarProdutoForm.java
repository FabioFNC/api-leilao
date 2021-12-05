package br.com.fabiofnc.apileilao.controller.form;

import br.com.fabiofnc.apileilao.entity.Produto;
import br.com.fabiofnc.apileilao.repository.ProdutoRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AtualizarProdutoForm {

    @NotBlank
    private String nome;
    @NotNull
    private BigDecimal valorInicial;
    private String descricao;

    public AtualizarProdutoForm(String nome, BigDecimal valorInicial, String descricao) {
        this.nome = nome;
        this.valorInicial = valorInicial;
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Produto atualizar(Long id, ProdutoRepository produtoRepository) {
        Produto produto = produtoRepository.getById(id);
        produto.setNome(this.nome);
        produto.setValorInicial(this.valorInicial);
        produto.setDescricao(this.descricao);
        produtoRepository.save(produto);
        return produto;
    }

}
