package br.com.fabiofnc.apileilao.controller.dto;

import br.com.fabiofnc.apileilao.entity.Usuario;
import br.com.fabiofnc.apileilao.repository.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDto {

    private Long id;
    private String nome;
    private String email;

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static List<UsuarioDto> converterTodos(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
    }

}
