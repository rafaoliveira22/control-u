package com.controlu.backend.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import java.time.OffsetDateTime;

public class AulaVO extends RepresentationModel<AlunoVO> {
    private String aulaId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime aulaAbertura;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime aulaFechamento;
    private String gradeId;
    private String salaId;

    @JsonIgnore
    private String dispositivoId;

    @JsonIgnore
    private String cartaoId;

    public AulaVO(){}

    public AulaVO(String aulaId, OffsetDateTime aulaAbertura, OffsetDateTime aulaFechamento, String gradeId, String salaId) {
        this.aulaId = aulaId;
        this.aulaAbertura = aulaAbertura;
        this.aulaFechamento = aulaFechamento;
        this.gradeId = gradeId;
        this.salaId = salaId;
    }

    public AulaVO(String aulaId, OffsetDateTime aulaAbertura, OffsetDateTime aulaFechamento, String gradeId, String salaId, String dispositivoId, String cartaoId) {
        this.aulaId = aulaId;
        this.aulaAbertura = aulaAbertura;
        this.aulaFechamento = aulaFechamento;
        this.gradeId = gradeId;
        this.salaId = salaId;
        this.dispositivoId = dispositivoId;
        this.cartaoId = cartaoId;
    }

    public String getAulaId() {
        return aulaId;
    }

    public void setAulaId(String aulaId) {
        this.aulaId = aulaId;
    }

    public OffsetDateTime getAulaAbertura() {
        return aulaAbertura;
    }

    public void setAulaAbertura(OffsetDateTime aulaAbertura) {
        this.aulaAbertura = aulaAbertura;
    }

    public OffsetDateTime getAulaFechamento() {
        return aulaFechamento;
    }

    public void setAulaFechamento(OffsetDateTime aulaFechamento) {
        this.aulaFechamento = aulaFechamento;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getSalaId() {
        return salaId;
    }

    public void setSalaId(String salaId) {
        this.salaId = salaId;
    }

    public String getDispositivoId() {
        return dispositivoId;
    }

    public void setDispositivoId(String dispositivoId) {
        this.dispositivoId = dispositivoId;
    }

    public String getCartaoId() {
        return cartaoId;
    }

    public void setCartaoId(String cartaoId) {
        this.cartaoId = cartaoId;
    }

    @Override
    public String toString() {
        return "AulaVO{" +
                "aulaId=" + aulaId +
                ", aulaAbertura=" + aulaAbertura +
                ", aulaFechamento=" + aulaFechamento +
                ", gradeId=" + gradeId +
                ", salaId='" + salaId + '\'' +
                ", dispositivoId='" + dispositivoId + '\'' +
                ", cartaoId='" + cartaoId + '\'' +
                '}';
    }
}
