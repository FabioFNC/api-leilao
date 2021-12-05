package br.com.fabiofnc.apileilao.controller.form;


import br.com.fabiofnc.apileilao.entity.Produto;
import br.com.fabiofnc.apileilao.entity.Usuario;
import br.com.fabiofnc.apileilao.repository.UsuarioRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProdutoForm {

    @NotBlank
    private String nome;
    @NotNull
    private BigDecimal valorInicial;
    @NotNull
    private Long idDoAnuciante;

    public ProdutoForm(String nome, BigDecimal valorInicial, Long idDoAnuciante) {
        this.nome = nome;
        this.valorInicial = valorInicial;
        this.idDoAnuciante = idDoAnuciante;
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

    public Long getIdDoAnuciante() {
        return idDoAnuciante;
    }

    public void setIdDoAnuciante(Long idDoAnuciante) {
        this.idDoAnuciante = idDoAnuciante;
    }

    public Produto converter(UsuarioRepository usuarioRepository) {
        Usuario usuario = usuarioRepository.getById(idDoAnuciante);
        return new Produto(this.nome, this.valorInicial, usuario);
    }
}
