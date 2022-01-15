package br.com.fabiofnc.apileilao.controller.dto.converter.custom;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.stereotype.Service;

import br.com.fabiofnc.apileilao.controller.ProdutoController;
import br.com.fabiofnc.apileilao.controller.dto.ProdutoDto;
import br.com.fabiofnc.apileilao.controller.dto.converter.DozerConverter;
import br.com.fabiofnc.apileilao.controller.form.ProdutoForm;
import br.com.fabiofnc.apileilao.entity.Produto;
import br.com.fabiofnc.apileilao.entity.Usuario;
import br.com.fabiofnc.apileilao.service.UsuarioService;

@Service
public class ProdutoConverter {
	
	@Autowired
	private UsuarioService usuarioService;
		
	public Produto convertFormToEntity(ProdutoForm form) {
        Usuario usuario = usuarioService.findById(form.getIdDoAnunciante());
        Produto produto = DozerConverter.parseObject(form, Produto.class);
        produto.setDonoDoProduto(usuario);
        return produto;
    }
	
	public ProdutoDto convertEntityToDTO(Produto produto) {
		ProdutoDto produtoDto = new ProdutoDto(produto);
		produtoDto.add(linkTo(methodOn(ProdutoController.class)
				.findById(produto.getId())).withSelfRel());
        
		produtoDto.add(linkTo(methodOn(ProdutoController.class)
			.findAll(null)).withRel(IanaLinkRelations.COLLECTION));
        
		produtoDto.add(linkTo(methodOn(ProdutoController.class)
    			.create(null, null)).withRel("create"));
        
		produtoDto.add(linkTo(methodOn(ProdutoController.class)
    			.update(produto.getId(), null)).withRel("update"));
        
		produtoDto.add(linkTo(methodOn(ProdutoController.class)
    			.delete(produto.getId())).withRel("delete"));
		
		produtoDto.add(linkTo(methodOn(ProdutoController.class)
    			.fecharNegociacaoDoProduto(produto.getId())).withRel("fechar_negociacao"));
        return produtoDto;
	}
	
	public Page<ProdutoDto> convertPageToDTO(Page<Produto> produtos) {
		return produtos.map(entity -> convertEntityToDTO(entity));
	}
	
	public List<ProdutoDto> convertListToDTO(List<Produto> produtos) {
		return produtos.stream().map(produto -> convertEntityToDTO(produto)).collect(Collectors.toList());
	}

}
