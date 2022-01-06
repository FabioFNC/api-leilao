package br.com.fabiofnc.apileilao.config.security;

import br.com.fabiofnc.apileilao.repository.UsuarioRepository;
import br.com.fabiofnc.apileilao.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //Conficuracoes de autenticacao.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Pegando a service que tem a logica de autenticacao.
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //Conficuracoes de Autorizacao.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        //Permitindo o acesso as seguintes rotas:
        .antMatchers(HttpMethod.GET ,"/api/**").permitAll()
        .antMatchers(HttpMethod.POST, "/api/auth").permitAll()
        //Para o resto das rotas e necesario a autenticacao.
        .anyRequest().authenticated()
        .and().csrf().disable()
        //Serve para não criar sessão
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
    }

    //Conficuracoes de recursos estaticos(js, css, html, imagens, etc.).
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**", "/swagger-ui/**");
    }

}