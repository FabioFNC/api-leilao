package br.com.fabiofnc.apileilao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fabiofnc.apileilao.controller.dto.ProdutoDto;
import br.com.fabiofnc.apileilao.controller.dto.converter.custom.ProdutoConverter;
import br.com.fabiofnc.apileilao.controller.form.AtualizarProdutoForm;
import br.com.fabiofnc.apileilao.entity.NegociaçaoDoProduto;
import br.com.fabiofnc.apileilao.entity.Produto;
import br.com.fabiofnc.apileilao.exception.EstagioDaNegociacaoException;
import br.com.fabiofnc.apileilao.exception.PropostaNaoEncontradaException;
import br.com.fabiofnc.apileilao.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoConverter converter;
		
	public Page<ProdutoDto> findAll(Pageable paginacao) {
		return produtoRepository.findAll(paginacao).map(p -> converter.convertEntityToDTO(p));
	}
	
	public Produto findById(Long id) {
		return produtoRepository.findById(id).orElseThrow(() -> new PropostaNaoEncontradaException("Produto de id " + id + " não encontrado."));
	}

	public void save(Produto produto) {
		produtoRepository.save(produto);
	}

	public void update(Long id, AtualizarProdutoForm form) {
		Produto produto = findById(id);
		form.atualizar(produto);
		produtoRepository.save(produto);
	}

	public void delete(Long id) {
		produtoRepository.delete(findById(id));
	}

	public void fecharNegociacaoDoProduto(Long id) {
		Produto produto = findById(id);
		if (produto.getNegociacaoDoProduto() == NegociaçaoDoProduto.ABERTO) {
			produto.setNegociacaoDoProduto(NegociaçaoDoProduto.FECHADO);
			save(produto);
		} else throw new EstagioDaNegociacaoException("Negociacao já está fechada");
	}
	

}
