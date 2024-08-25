package com.controlu.backend.vo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

public class SalaVO extends RepresentationModel<SalaVO> {
    private String salaId;
    private String salaNome;
    private String dispositivoId;

    public SalaVO(String salaId, String salaNome, String dispositivoId) {
        this.salaId = salaId;
        this.salaNome = salaNome;
        this.dispositivoId = dispositivoId;
    }

    public SalaVO(){}

    public String getSalaId() {
        return salaId;
    }

    public void setSalaId(String salaId) {
        this.salaId = salaId;
    }

    public String getSalaNome() {
        return salaNome;
    }

    public void setSalaNome(String salaNome) {
        this.salaNome = salaNome;
    }

    public String getDispositivoId() {
        return dispositivoId;
    }

    public void setDispositivoId(String dispositivoId) {
        this.dispositivoId = dispositivoId;
    }
}
