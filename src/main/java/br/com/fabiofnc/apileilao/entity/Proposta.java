package br.com.fabiofnc.apileilao.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "propostas")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    private String autor;
    private String mensagem;
    private final LocalDateTime dataDaProposta = LocalDateTime.now();
    @ManyToOne(cascade = CascadeType.ALL)
    private Produto produto;
    @ManyToOne
    private Usuario usuario;

    public Proposta(BigDecimal valor, String mensagem, Produto produto, Usuario usuario) {
        this.valor = valor;
        this.mensagem = mensagem;
        this.autor = usuario.getNome();
        this.produto = produto;
        this.usuario = usuario;
    }

}
