package br.com.fabiofnc.apileilao.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
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

import br.com.fabiofnc.apileilao.repository.UsuarioRepository;
import br.com.fabiofnc.apileilao.service.TokenService;

@EnableWebSecurity
@Configuration
@Profile(value = "dev")
public class DevSecurityConfigurations extends WebSecurityConfigurerAdapter {

    //Conficuracoes de Autorizacao.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
        //Permitindo o acesso as seguintes rotas:
        .antMatchers("/**").permitAll()
        .anyRequest().authenticated()
        .and().csrf().disable();
    }

}