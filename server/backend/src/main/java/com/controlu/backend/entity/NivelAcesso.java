package com.controlu.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "nivel_acesso")
public class NivelAcesso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nivel_acesso_id")
    private Integer nivelAcessoId;

    @Column(name = "nivel_acesso_nome")
    private String nivelAcessoNome;

    public NivelAcesso(){}

    public NivelAcesso(Integer nivelAcessoId, String nivelAcessoNome) {
        this.nivelAcessoId = nivelAcessoId;
        this.nivelAcessoNome = nivelAcessoNome;
    }

    public Integer getNivelAcessoId() {
        return nivelAcessoId;
    }

    public void setNivelAcessoId(Integer nivelAcessoId) {
        this.nivelAcessoId = nivelAcessoId;
    }

    public String getNivelAcessoNome() {
        return nivelAcessoNome;
    }

    public void setNivelAcessoNome(String nivelAcessoNome) {
        this.nivelAcessoNome = nivelAcessoNome;
    }
}
