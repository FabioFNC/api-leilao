package br.com.fabiofnc.apileilao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fabiofnc.apileilao.controller.dto.UsuarioDto;
import br.com.fabiofnc.apileilao.controller.dto.converter.custom.UsuarioConverter;
import br.com.fabiofnc.apileilao.controller.form.UsuarioForm;
import br.com.fabiofnc.apileilao.entity.Usuario;
import br.com.fabiofnc.apileilao.exception.UsuarioNaoEncontradoException;
import br.com.fabiofnc.apileilao.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioConverter usuarioConverter;
	
	public Page<UsuarioDto> findAll(Pageable paginacao) {
		return usuarioConverter.convertPageToDTO(usuarioRepository.findAll(paginacao));
	}
	
	public Usuario findById(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario de id " + id + " nao foi encontrado"));
	}
	
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public Usuario update(Long id, UsuarioForm form) {
		Usuario usuario = findById(id);
		form.atualizar(usuario);
		usuarioRepository.save(usuario);
		return usuario;
	}
	
	public void delete(Long id) {
		usuarioRepository.delete(findById(id));
	}

}
