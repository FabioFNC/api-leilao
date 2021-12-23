package br.com.fabiofnc.apileilao.controller;

import javax.validation.Valid;

<<<<<<< Updated upstream
import br.com.fabiofnc.apileilao.config.security.TokenService;
=======
>>>>>>> Stashed changes
import br.com.fabiofnc.apileilao.controller.dto.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabiofnc.apileilao.controller.form.LoginForm;
<<<<<<< Updated upstream
=======
import br.com.fabiofnc.apileilao.service.TokenService;
>>>>>>> Stashed changes

@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    //Pegando as informações do login
    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        try {
            Authentication authenticate = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authenticate);
            return ResponseEntity.ok().body(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
}