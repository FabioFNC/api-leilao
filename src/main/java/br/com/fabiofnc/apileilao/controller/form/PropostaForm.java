package br.com.fabiofnc.apileilao.controller.form;

import br.com.fabiofnc.apileilao.entity.Produto;
import br.com.fabiofnc.apileilao.entity.Proposta;
import br.com.fabiofnc.apileilao.entity.Usuario;
import br.com.fabiofnc.apileilao.repository.ProdutoRepository;
import br.com.fabiofnc.apileilao.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropostaForm {

    @NotNull
    private BigDecimal valor;
    private String mensagem;
    @NotNull
    private Long idDoProdutoRelacionado;
    @NotNull
    private Long idDoUsuarioRelacionado;

    public Proposta converter(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository) {
        Produto produto = produtoRepository.getById(this.idDoProdutoRelacionado);
        Usuario usuario = usuarioRepository.getById(this.idDoUsuarioRelacionado);
        return new Proposta(this.valor, this.mensagem, produto, usuario);
    }
    
}
