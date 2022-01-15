package br.com.fabiofnc.apileilao.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.RepresentationModel;

import br.com.fabiofnc.apileilao.entity.Produto;
import br.com.fabiofnc.apileilao.entity.Proposta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "id")
@NoArgsConstructor
public class ProdutoDto extends RepresentationModel<ProdutoDto>{

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal valorInicial;
    private String negociacaoDoProduto;
    private LocalDate dataDeLeilao;
    private String nomeDoAnunciante;
    private List<Long> idDasPropostas;
    private Long IdDonoDoProduto;

	public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.valorInicial = produto.getValorInicial();
        this.descricao = produto.getDescricao();
        this.negociacaoDoProduto = String.valueOf(produto.getNegociacaoDoProduto());
        this.dataDeLeilao = produto.getDataDeLeilao();
        this.nomeDoAnunciante = produto.getDonoDoProduto().getNome();
        this.idDasPropostas = produto.getPropostas().stream().map(Proposta::getId).collect(Collectors.toList());
        this.IdDonoDoProduto = produto.getId();
    }

}
