package br.com.fabiofnc.apileilao.controller.dto.converter.custom;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.stereotype.Service;

import br.com.fabiofnc.apileilao.controller.UsuarioController;
import br.com.fabiofnc.apileilao.controller.dto.UsuarioDto;
import br.com.fabiofnc.apileilao.controller.dto.converter.DozerConverter;
import br.com.fabiofnc.apileilao.controller.form.UsuarioForm;
import br.com.fabiofnc.apileilao.entity.Usuario;

@Service
public class UsuarioConverter {
	
	public UsuarioDto convertEntityToDTO(Usuario usuario) {
		UsuarioDto usuarioDTO = DozerConverter.parseObject(usuario, UsuarioDto.class);
		usuarioDTO.add(linkTo(methodOn(UsuarioController.class)
				.findById(usuario.getId())).withSelfRel());
        
		usuarioDTO.add(linkTo(methodOn(UsuarioController.class)
			.findAll(null, null)).withRel(IanaLinkRelations.COLLECTION));
        
		usuarioDTO.add(linkTo(methodOn(UsuarioController.class)
    			.create(null, null)).withRel("create"));
        
		usuarioDTO.add(linkTo(methodOn(UsuarioController.class)
    			.update(usuario.getId(), null)).withRel("update"));
        
		usuarioDTO.add(linkTo(methodOn(UsuarioController.class)
    			.delete(usuario.getId())).withRel("delete"));
        return usuarioDTO;
	}
	
	public Usuario convertFormToEntity(UsuarioForm form) {
		return DozerConverter.parseObject(form, Usuario.class);
    }
	
	public Page<UsuarioDto> convertPageToDTO(Page<Usuario> usuarios) {
		List<UsuarioDto> dtos = usuarios.stream().map(user -> convertEntityToDTO(user)).collect(Collectors.toList());
		Page<UsuarioDto> dtosPage = new PageImpl<UsuarioDto>(dtos, usuarios.getPageable(), dtos.size());
		return dtosPage;
	}
	
	public List<UsuarioDto> convertListToDTO(List<Usuario> usuarios) {
		return usuarios.stream().map(person -> convertEntityToDTO(person)).collect(Collectors.toList());
	
	}

}