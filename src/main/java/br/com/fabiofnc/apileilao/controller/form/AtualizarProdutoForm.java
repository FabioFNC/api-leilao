package br.com.fabiofnc.apileilao.controller.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.fabiofnc.apileilao.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarProdutoForm {

    @NotBlank
    private String nome;
    @NotNull
    private BigDecimal valorInicial;
    private String descricao;

    public void atualizar(Produto produto) {
        produto.setNome(this.nome);
        produto.setValorInicial(this.valorInicial);
        produto.setDescricao(this.descricao);
    }

}
