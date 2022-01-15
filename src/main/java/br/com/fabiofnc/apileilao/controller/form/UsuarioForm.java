package br.com.fabiofnc.apileilao.controller.form;

import javax.validation.constraints.NotBlank;

import br.com.fabiofnc.apileilao.entity.Usuario;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class UsuarioForm {

    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
    
    public void atualizar(Usuario usuario) {
        usuario.setNome(this.nome);
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha);
    }
    
}
