package br.com.fabiofnc.apileilao.controller.form;

import br.com.fabiofnc.apileilao.entity.Proposta;
import br.com.fabiofnc.apileilao.repository.PropostaRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarPropostaForm {

    @NotNull
    private BigDecimal valor;
    private String mensagem;

    public void atualizar(Proposta proposta) {
        proposta.setValor(this.valor);
        proposta.setMensagem(this.mensagem);
    }

    public Proposta converter(Long id, PropostaRepository repository) {
        return repository.getById(id);
    }
}
