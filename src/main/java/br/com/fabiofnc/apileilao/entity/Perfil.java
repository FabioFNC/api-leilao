package br.com.fabiofnc.apileilao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;

@Entity
@Table(name = "perfis")
public class Perfil implements GrantedAuthority {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeDoPerfil;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDoPerfil() {
        return nomeDoPerfil;
    }

    public void setNomeDoPerfil(String nomeDoPerfil) {
        this.nomeDoPerfil = nomeDoPerfil;
    }

    @Override
    public String getAuthority() {
        return this.nomeDoPerfil;
    }

}
