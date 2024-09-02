package com.controlu.backend.vo;

import org.springframework.hateoas.RepresentationModel;

public class NivelAcessoVO extends RepresentationModel<NivelAcessoVO> {
    private Integer nivelAcessoId;
    private String nivelAcessoNome;

    public NivelAcessoVO(){}

    public NivelAcessoVO(Integer nivelAcessoId, String nivelAcessoNome) {
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
