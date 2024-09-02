package com.controlu.backend.vo;

import org.springframework.hateoas.RepresentationModel;

public class UsuarioVO extends RepresentationModel<UsuarioVO> {
    private Integer usuarioId;

    private String usuarioNome;

    private String usuarioSenha;

    private Integer nivelAcessoId;

    public UsuarioVO(){}

    public UsuarioVO(Integer usuarioId, String usuarioNome, String usuarioSenha, Integer nivelAcessoId) {
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
}

