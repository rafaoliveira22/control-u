package com.controlu.backend.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import java.time.OffsetDateTime;

public class AulaVO extends RepresentationModel<AlunoVO> {
    private Integer aulaId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime aulaAbertura;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime aulaFechamento;
    private Integer gradeId;
    @JsonIgnore
    private String dispositivoId;
    private String salaId;

    public AulaVO(){}

    public AulaVO(Integer aulaId, OffsetDateTime aulaAbertura, OffsetDateTime aulaFechamento, Integer gradeId, String salaId) {
        this.aulaId = aulaId;
        this.aulaAbertura = aulaAbertura;
        this.aulaFechamento = aulaFechamento;
        this.gradeId = gradeId;
        this.salaId = salaId;
    }

    public AulaVO(Integer aulaId, OffsetDateTime aulaAbertura, OffsetDateTime aulaFechamento, Integer gradeId, String salaId, String dispositivoId) {
        this.aulaId = aulaId;
        this.aulaAbertura = aulaAbertura;
        this.aulaFechamento = aulaFechamento;
        this.gradeId = gradeId;
        this.salaId = salaId;
        this.dispositivoId = dispositivoId;
    }

    public Integer getAulaId() {
        return aulaId;
    }

    public void setAulaId(Integer aulaId) {
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

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
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
}
