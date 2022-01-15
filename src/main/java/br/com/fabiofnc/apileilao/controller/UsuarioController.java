package br.com.fabiofnc.apileilao.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fabiofnc.apileilao.controller.dto.UsuarioDto;
import br.com.fabiofnc.apileilao.controller.dto.converter.custom.UsuarioConverter;
import br.com.fabiofnc.apileilao.controller.form.UsuarioForm;
import br.com.fabiofnc.apileilao.entity.Usuario;
import br.com.fabiofnc.apileilao.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private UsuarioConverter converter;
    
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<UsuarioDto>>> findAll(PagedResourcesAssembler<UsuarioDto> assembler, @PageableDefault(page=0, size=10, sort="id",
    		direction = Sort.Direction.ASC) Pageable paginacao) {
        Page<UsuarioDto> usuarios = usuarioService.findAll(paginacao);
        return ResponseEntity.ok().body(assembler.toModel(usuarios));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> findById(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        return ResponseEntity.ok().body(converter.convertEntityToDTO(usuario));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDto> create(@RequestBody @Valid UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder) {
        Usuario usuario = converter.convertFormToEntity(usuarioForm);
        usuarioService.save(usuario);
        URI uri = uriBuilder.path("/api/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(converter.convertEntityToDTO(usuario));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioDto> update(@PathVariable Long id, @RequestBody @Valid UsuarioForm form ) {
    	usuarioService.update(id, form);
        return ResponseEntity.ok().body(converter.convertEntityToDTO(usuarioService.findById(id)));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id ) {
    	usuarioService.delete(id);
    	return ResponseEntity.status(HttpStatus.OK).build();
    }


}
