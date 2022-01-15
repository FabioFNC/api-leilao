package br.com.fabiofnc.apileilao.controller.dto;

import br.com.fabiofnc.apileilao.entity.Proposta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class PropostaDto extends RepresentationModel<PropostaDto>{

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

    public static Page<PropostaDto> converterTodas(Page<Proposta> propostas) {
        return propostas.map(PropostaDto::new);
    }

}
