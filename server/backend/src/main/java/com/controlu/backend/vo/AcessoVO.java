package com.controlu.backend.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class AcessoVO extends RepresentationModel<AcessoVO> {
    private Integer acessoId;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime acessoEntrada;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime acessoSaida;

    private String dispositivoId ;

    private String alunoId;

    public AcessoVO(){}


    public AcessoVO(Integer acessoId, OffsetDateTime acessoEntrada, OffsetDateTime acessoSaida, String dispositivoId, String alunoId) {
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

    public OffsetDateTime getAcessoEntrada() {
        return acessoEntrada;
    }

    public void setAcessoEntrada(OffsetDateTime acessoEntrada) {
        this.acessoEntrada = acessoEntrada;
    }

    public OffsetDateTime getAcessoSaida() {
        return acessoSaida;
    }

    public void setAcessoSaida(OffsetDateTime acessoSaida) {
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

    @Override
    public String toString() {
        return "AcessoVO{" +
                "acessoId=" + acessoId +
                ", acessoEntrada=" + acessoEntrada +
                ", acessoSaida=" + acessoSaida +
                ", dispositivoId='" + dispositivoId + '\'' +
                ", alunoId='" + alunoId + '\'' +
                '}';
    }
}
