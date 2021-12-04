package br.com.fabiofnc.apileilao.controller;

import br.com.fabiofnc.apileilao.controller.dto.UsuarioDto;
import br.com.fabiofnc.apileilao.controller.form.UsuarioForm;
import br.com.fabiofnc.apileilao.entity.Usuario;
import br.com.fabiofnc.apileilao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> pegarUsuarios() {
        Usuario usuario = new Usuario("teste", "teste@gmail.com", "teste123");
        usuarioRepository.save(usuario);
        List<Usuario> usuarios = new ArrayList<Usuario>(usuarioRepository.findAll());
        return ResponseEntity.ok().body(UsuarioDto.converter(usuarios));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> pegarUmUsuarios(@PathVariable Long id) {
        UsuarioDto usuarioDto = new UsuarioDto(usuarioRepository.getById(id));
        return ResponseEntity.ok().body(usuarioDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDto> adicionarUsuario(@RequestBody @Valid UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder) {
        Usuario usuario = usuarioForm.converter();
        usuarioRepository.save(usuario);
        URI uri = uriBuilder.path("/api/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioDto> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioForm form ) {
        Usuario usuario = form.atualizar(id, usuarioRepository);
        return ResponseEntity.ok().body(new UsuarioDto(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarUsuarios(@PathVariable Long id ) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
