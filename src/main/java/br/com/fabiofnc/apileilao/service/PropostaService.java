package br.com.fabiofnc.apileilao.service;

import br.com.fabiofnc.apileilao.controller.form.AtualizarPropostaForm;
import br.com.fabiofnc.apileilao.entity.Proposta;
import br.com.fabiofnc.apileilao.exception.PropostaException;
import br.com.fabiofnc.apileilao.exception.PropostaInvalida;
import br.com.fabiofnc.apileilao.repository.PropostaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PropostaService {

    private PropostaRepository propostaRepository;

    public PropostaService(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    public void save(Proposta novaProposta) {
        Optional<BigDecimal> maiorPropostaAnterior = novaProposta.getProduto().maiorProposta();
        if (maiorPropostaAnterior.isPresent()) {
            if (novaProposta.getValor().compareTo(maiorPropostaAnterior.get()) > 0) {
                propostaRepository.save(novaProposta);
            } else {
                throw new PropostaInvalida("Valor da propostas atual está abaixo da ultima.");
            }
        } else {
            throw new PropostaException("Nao a nenhuma proposta.");
        }
    }

    public void update(Long idProposta, AtualizarPropostaForm form) {
        Proposta novaProposta = propostaRepository.getById(idProposta);
        Long idUser = novaProposta.getUsuario().getId();
        Long idProd = novaProposta.getProduto().getId();
        Optional<BigDecimal> maiorPropostaAnterior = novaProposta.getProduto().maiorProposta(idProd, idUser);
        form.atualizar(novaProposta);
        if (maiorPropostaAnterior.isPresent()) {
            if (novaProposta.getValor().compareTo(maiorPropostaAnterior.get()) > 0) {
                propostaRepository.save(novaProposta);
            } else {
                throw new PropostaInvalida("Valor da propostas atual está abaixo da ultima.");
            }
        } else {
            throw new PropostaException("Nao a nenhuma proposta.");
        }
    }
}
