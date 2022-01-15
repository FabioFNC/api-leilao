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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString(exclude = "id")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
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
    private NegociaçaoDoProduto negociacaoDoProduto = NegociaçaoDoProduto.ABERTO;
    private final LocalDate dataDeLeilao = LocalDate.now();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Proposta> propostas = new ArrayList<>();
    @ManyToOne
    private Usuario donoDoProduto;

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

}
