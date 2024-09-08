package com.controlu.backend.entity;

import com.controlu.backend.utils.Defines;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "usuario_nome")
    private String usuarioNome;

    @Column(name = "usuario_senha")
    private String usuarioSenha;

    @Column(name = "nivel_acesso_id")
    private Integer nivelAcessoId;

    public Usuario(){}

    public Usuario(Integer usuarioId, String usuarioNome, String usuarioSenha, Integer nivelAcessoId) {
        this.usuarioId = usuarioId;
        this.usuarioNome = usuarioNome;
        this.usuarioSenha = usuarioSenha;
        this.nivelAcessoId = nivelAcessoId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public String getUsuarioSenha() {
        return usuarioSenha;
    }

    public void setUsuarioSenha(String usuarioSenha) {
        this.usuarioSenha = usuarioSenha;
    }

    public Integer getNivelAcessoId() {
        return nivelAcessoId;
    }

    public void setNivelAcessoId(Integer nivelAcessoId) {
        this.nivelAcessoId = nivelAcessoId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(Objects.equals(this.nivelAcessoId, Defines.ROLE_USUARIO_ADM)){
            return List.of(new SimpleGrantedAuthority("ROLE_ADM"), new SimpleGrantedAuthority("ROLE_OPERADOR"), new SimpleGrantedAuthority("ROLE_COMUM"));
        } if(Objects.equals(this.nivelAcessoId, Defines.ROLE_USUARIO_OPERADOR)){
            return List.of(new SimpleGrantedAuthority("ROLE_OPERADOR"), new SimpleGrantedAuthority("ROLE_COMUM"));
        } else{
            return List.of(new SimpleGrantedAuthority("ROLE_COMUM"));
        }
    }

    @Override
    public String getPassword() {
        return usuarioSenha;
    }

    @Override
    public String getUsername() {
        return usuarioNome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
        //return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
        //return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        //return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return true;
        //return UserDetails.super.isEnabled();
    }
}
