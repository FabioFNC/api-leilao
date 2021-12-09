package br.com.fabiofnc.apileilao.controller.dto;

import br.com.fabiofnc.apileilao.entity.Proposta;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class PropostaDto {

    private Long id;
    private BigDecimal valor;
    private String autor;
    private String mensagem;
    private Long idDoProdutoRelacionado;
    private Long idDoUsuarioRelacionado;

    public PropostaDto(Proposta proposta) {
        this.id = proposta.getId();
        this.valor = proposta.getValor();
        this.autor = proposta.getAutor();
        this.mensagem = proposta.getMensagem();
        this.idDoProdutoRelacionado = proposta.getProduto().getId();
        this.idDoUsuarioRelacionado = proposta.getUsuario().getId();
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getAutor() {
        return autor;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Long getIdDoProdutoRelacionado() {
        return idDoProdutoRelacionado;
    }

    public Long getIdDoUsuarioRelacionado() {
        return idDoUsuarioRelacionado;
    }

    public static List<PropostaDto> converterTodas(List<Proposta> propostas) {
        return propostas.stream().map(PropostaDto::new).collect(Collectors.toList());
    }

}
