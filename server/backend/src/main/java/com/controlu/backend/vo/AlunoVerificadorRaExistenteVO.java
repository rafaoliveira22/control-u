package com.controlu.backend.vo;

public class AlunoVerificadorRaExistenteVO {
    private boolean estaRegistrado;

    public AlunoVerificadorRaExistenteVO(){}

    public AlunoVerificadorRaExistenteVO(boolean estaRegistrado) {
        this.estaRegistrado = estaRegistrado;
    }

    public boolean getEstaRegistrado() {
        return estaRegistrado;
    }

    public void setEstaRegistrado(boolean estaRegistrado) {
        this.estaRegistrado = estaRegistrado;
    }
}
