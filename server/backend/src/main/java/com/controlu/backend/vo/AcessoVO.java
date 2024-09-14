package com.controlu.backend.vo;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

public class AcessoVO extends RepresentationModel<AcessoVO> {
    private Integer acessoId;


    private LocalDateTime acessoEntrada;

    private LocalDateTime acessoSaida;

    private String dispositivoId ;

    private String alunoId;

    public AcessoVO(){}


    public AcessoVO(Integer acessoId, LocalDateTime acessoEntrada, LocalDateTime acessoSaida, String dispositivoId, String alunoId) {
        this.acessoId = acessoId;
        this.acessoEntrada = acessoEntrada;
        this.acessoSaida = acessoSaida;
        this.dispositivoId = dispositivoId;
        this.alunoId = alunoId;
    }

    public Integer getAcessoId() {
        return acessoId;
    }

    public void setAcessoId(Integer acessoId) {
        this.acessoId = acessoId;
    }

    public LocalDateTime getAcessoEntrada() {
        return acessoEntrada;
    }

    public void setAcessoEntrada(LocalDateTime acessoEntrada) {
        this.acessoEntrada = acessoEntrada;
    }

    public LocalDateTime getAcessoSaida() {
        return acessoSaida;
    }

    public void setAcessoSaida(LocalDateTime acessoSaida) {
        this.acessoSaida = acessoSaida;
    }

    public String getDispositivoId() {
        return dispositivoId;
    }

    public void setDispositivoId(String dispositivoId) {
        this.dispositivoId = dispositivoId;
    }

    public String getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(String alunoId) {
        this.alunoId = alunoId;
    }
}
