package br.com.fabiofnc.apileilao.controller;

import br.com.fabiofnc.apileilao.controller.dto.UsuarioDto;
import br.com.fabiofnc.apileilao.controller.form.UsuarioForm;
import br.com.fabiofnc.apileilao.entity.Usuario;
import br.com.fabiofnc.apileilao.repository.UsuarioRepository;
import br.com.fabiofnc.apileilao.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping
    public ResponseEntity<Page<UsuarioDto>> pegarUsuarios(@PageableDefault(page=0, size=10, sort="id",
                                                            direction = Sort.Direction.ASC) Pageable paginacao) {
        Page<Usuario> usuarios = usuarioService.findAll(paginacao);
        return ResponseEntity.ok().body(UsuarioDto.converterTodos(usuarios));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> pegarUmUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        return ResponseEntity.ok().body(new UsuarioDto(usuario));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDto> adicionarUsuario(@RequestBody @Valid UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder) {
        Usuario usuario = usuarioForm.converter();
        usuarioService.save(usuario);
        URI uri = uriBuilder.path("/api/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioDto> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioForm form ) {
    	usuarioService.update(id, form);
        return ResponseEntity.ok().body(new UsuarioDto(usuarioService.findById(id)));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id ) {
    	usuarioService.delete(id);
    	return ResponseEntity.status(HttpStatus.OK).build();
    }


}
