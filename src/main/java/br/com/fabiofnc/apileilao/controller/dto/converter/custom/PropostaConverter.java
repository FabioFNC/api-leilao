package br.com.fabiofnc.apileilao.controller.dto.converter.custom;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.stereotype.Service;

import br.com.fabiofnc.apileilao.controller.PropostaController;
import br.com.fabiofnc.apileilao.controller.dto.PropostaDto;
import br.com.fabiofnc.apileilao.controller.dto.converter.DozerConverter;
import br.com.fabiofnc.apileilao.controller.form.PropostaForm;
import br.com.fabiofnc.apileilao.entity.Produto;
import br.com.fabiofnc.apileilao.entity.Proposta;
import br.com.fabiofnc.apileilao.entity.Usuario;
import br.com.fabiofnc.apileilao.service.ProdutoService;
import br.com.fabiofnc.apileilao.service.UsuarioService;

@Service
public class PropostaConverter {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ProdutoService produtoService;
		
	public Proposta convertFormToEntity(PropostaForm form) {
        Usuario usuario = usuarioService.findById(form.getIdDoUsuarioRelacionado());
        Produto produto = produtoService.findById(form.getIdDoProdutoRelacionado());
        Proposta proposta = DozerConverter.parseObject(form, Proposta.class);
        proposta.setUsuario(usuario);
        proposta.setAutor(usuario.getNome());
        proposta.setProduto(produto);
        return proposta;
    }
	
	public PropostaDto convertEntityToDTO(Proposta proposta) {
		PropostaDto propostaDto = new PropostaDto(proposta);
		propostaDto.add(linkTo(methodOn(PropostaController.class)
				.findById(proposta.getId())).withSelfRel());
        
		propostaDto.add(linkTo(methodOn(PropostaController.class)
			.findAll(null)).withRel(IanaLinkRelations.COLLECTION));
        
		propostaDto.add(linkTo(methodOn(PropostaController.class)
    			.create(null, null)).withRel("create"));
        
		propostaDto.add(linkTo(methodOn(PropostaController.class)
    			.update(proposta.getId(), null)).withRel("update"));
        
		propostaDto.add(linkTo(methodOn(PropostaController.class)
    			.delete(proposta.getId())).withRel("delete"));

        return propostaDto;
	}
	
	public Page<PropostaDto> convertPageToDTO(Page<Proposta> produtos) {
		return produtos.map(entity -> convertEntityToDTO(entity));
	}
	
	public List<PropostaDto> convertListToDTO(List<Proposta> produtos) {
		return produtos.stream().map(produto -> convertEntityToDTO(produto)).collect(Collectors.toList());
	}

}
