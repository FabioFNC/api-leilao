package br.com.fabiofnc.apileilao.controller.form;

import br.com.fabiofnc.apileilao.entity.Proposta;
import br.com.fabiofnc.apileilao.repository.PropostaRepository;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AtualizarPropostaForm {

    @NotNull
    private BigDecimal valor;
    private String mensagem;

    public AtualizarPropostaForm(BigDecimal valor, String mensagem) {
        this.valor = valor;
        this.mensagem = mensagem;
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


    public Proposta atualizar(Long id, PropostaRepository propostaRepository) {
        Proposta proposta = propostaRepository.getById(id);
        proposta.setValor(this.valor);
        proposta.setMensagem(this.mensagem);
        return proposta;
    }
}