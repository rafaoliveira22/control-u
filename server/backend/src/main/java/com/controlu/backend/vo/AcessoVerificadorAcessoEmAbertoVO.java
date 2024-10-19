package com.controlu.backend.vo;

public class AcessoVerificadorAcessoEmAbertoVO {
    private boolean temAcessoEmAberto;

    public AcessoVerificadorAcessoEmAbertoVO(){}

    public AcessoVerificadorAcessoEmAbertoVO(boolean temAcessoEmAberto) {
        this.temAcessoEmAberto = temAcessoEmAberto;
    }

    public boolean getTemAcessoEmAberto() {
        return temAcessoEmAberto;
    }

    public void setTemAcessoEmAberto(boolean temAcessoEmAberto) {
        this.temAcessoEmAberto = temAcessoEmAberto;
    }
}
