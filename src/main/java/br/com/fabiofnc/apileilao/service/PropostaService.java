package br.com.fabiofnc.apileilao.service;

import br.com.fabiofnc.apileilao.controller.form.AtualizarPropostaForm;
import br.com.fabiofnc.apileilao.entity.Proposta;
import br.com.fabiofnc.apileilao.exception.PropostaException;
import br.com.fabiofnc.apileilao.exception.PropostaInvalida;
import br.com.fabiofnc.apileilao.exception.PropostaNaoEncontradaException;
import br.com.fabiofnc.apileilao.repository.PropostaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PropostaService {

	@Autowired
    private PropostaRepository propostaRepository;

    
    public Proposta findById(Long id) {
    	return propostaRepository.findById(id).orElseThrow(() -> new PropostaNaoEncontradaException("Proposta de id " + id + " não encontrada."));
    }
    
    public Page<Proposta> findAll(Pageable paginacao) {
    	return propostaRepository.findAll(paginacao);
    }

    public void save(Proposta novaProposta) {
        BigDecimal maiorPropostaAnterior = novaProposta.getProduto().maiorProposta().orElse(new BigDecimal("0"));
        boolean propostaEhValida = novaProposta.getValor().compareTo(maiorPropostaAnterior) > 0;
        if(propostaEhValida) {
        	propostaRepository.save(novaProposta);
        } else {
        	throw new PropostaInvalida("Valor da propostas atual está abaixo da ultima.");
        }
    }

    public void update(Long idProposta, AtualizarPropostaForm form) {
        Proposta proposta = findById(idProposta);
        BigDecimal maiorPropostaAnterior = proposta.getProduto().maiorProposta().orElse(null);
        form.atualizar(proposta);
        if (!maiorPropostaAnterior.equals(null)) {
        	boolean propostaEhValida = proposta.getValor().compareTo(maiorPropostaAnterior) > 0;
        	if (propostaEhValida) {
                propostaRepository.save(proposta);
            } else throw new PropostaInvalida("Valor da propostas atual está abaixo da ultima.");
        } else throw new PropostaException("Nao a nenhuma proposta.");
    }
    
    public void delete(Long id) {
    	propostaRepository.delete(findById(id));
    }
}
