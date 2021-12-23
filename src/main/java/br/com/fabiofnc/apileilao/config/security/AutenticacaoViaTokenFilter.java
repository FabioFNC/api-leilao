package br.com.fabiofnc.apileilao.config.security;

<<<<<<< Updated upstream
import br.com.fabiofnc.apileilao.entity.Usuario;
import br.com.fabiofnc.apileilao.repository.UsuarioRepository;
=======
>>>>>>> Stashed changes
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

<<<<<<< Updated upstream
=======
import br.com.fabiofnc.apileilao.entity.Usuario;
import br.com.fabiofnc.apileilao.repository.UsuarioRepository;
import br.com.fabiofnc.apileilao.service.TokenService;

>>>>>>> Stashed changes
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(request, response);
        boolean valido = tokenService.tokenValido(token);
        if (valido) {
            autenticarCliente(token);
        }
<<<<<<< Updated upstream
        //Se nao tiver valido o spring bai barrar a requisicao
=======
        //Se não estiver valido o spring vai barrar a requisicao
>>>>>>> Stashed changes
        filterChain.doFilter(request, response);
    }

    private void autenticarCliente(String token) {
        //Pegando id do token
        Long idUsuario = tokenService.getIdUsuario(token);
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                usuario, null, usuario.getAuthorities());
<<<<<<< Updated upstream
        //Considera que o usuario esta autenticado
=======
        //Considerar que o usuario esta autenticado
>>>>>>> Stashed changes
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }

}
