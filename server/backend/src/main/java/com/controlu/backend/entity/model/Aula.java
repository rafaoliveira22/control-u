package com.controlu.backend.entity.model;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "aula")
public class Aula {
    @Id
    @Column(name = "aula_id")
    private String aulaId;

    @Column(name = "aula_abertura")
    private OffsetDateTime aulaAbertura;

    @Column(name = "aula_fechamento")
    private OffsetDateTime aulaFechamento;

    @Column(name = "grade_id")
    private String gradeId;

    @Column(name = "sala_id")
    private String salaId;

    public Aula(){}

    public Aula(String aulaId, OffsetDateTime aulaAbertura, OffsetDateTime aulaFechamento, String gradeId, String salaId) {
        this.aulaId = aulaId;
        this.aulaAbertura = aulaAbertura;
        this.aulaFechamento = aulaFechamento;
        this.gradeId = gradeId;
        this.salaId = salaId;
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

    @Override
    public String toString() {
        return "Aula{" +
                "aulaId=" + aulaId +
                ", aulaAbertura=" + aulaAbertura +
                ", aulaFechamento=" + aulaFechamento +
                ", gradeId=" + gradeId +
                ", salaId='" + salaId + '\'' +
                '}';
    }
}
