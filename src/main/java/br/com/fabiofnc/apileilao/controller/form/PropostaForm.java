package br.com.fabiofnc.apileilao.controller.form;

import br.com.fabiofnc.apileilao.entity.Produto;
import br.com.fabiofnc.apileilao.entity.Proposta;
import br.com.fabiofnc.apileilao.entity.Usuario;
import br.com.fabiofnc.apileilao.repository.ProdutoRepository;
import br.com.fabiofnc.apileilao.repository.UsuarioRepository;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PropostaForm {

    @NotNull
    private BigDecimal valor;
    private String mensagem;
    @NotNull
    private Long idDoProdutoRelacionado;
    @NotNull
    private Long idDoUsuarioRelacionado;

    public PropostaForm(BigDecimal valor, String mensagem, Long idDoProdutoRelacionado, Long idDoUsuarioRelacionado) {
        this.valor = valor;
        this.mensagem = mensagem;
        this.idDoProdutoRelacionado = idDoProdutoRelacionado;
        this.idDoUsuarioRelacionado = idDoUsuarioRelacionado;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Long getIdDoProdutoRelacionado() {
        return idDoProdutoRelacionado;
    }

    public void setIdDoProdutoRelacionado(Long idDoProdutoRelacionado) {
        this.idDoProdutoRelacionado = idDoProdutoRelacionado;
    }

    public Long getIdDoUsuarioRelacionado() {
        return idDoUsuarioRelacionado;
    }

    public void setIdDoUsuarioRelacionado(Long idDoUsuarioRelacionado) {
        this.idDoUsuarioRelacionado = idDoUsuarioRelacionado;
    }

    public Proposta converter(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository) {
        Produto produto = produtoRepository.getById(this.idDoProdutoRelacionado);
        Usuario usuario = usuarioRepository.getById(this.idDoUsuarioRelacionado);
        return new Proposta(this.valor, this.mensagem, produto, usuario);
    }
}
