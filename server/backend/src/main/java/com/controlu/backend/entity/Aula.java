package com.controlu.backend.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "aula")
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aula_id")
    private Integer aulaId;

    @Column(name = "aula_abertura")
    private OffsetDateTime aulaAbertura;

    @Column(name = "aula_fechamento")
    private OffsetDateTime aulaFechamento;

    @Column(name = "grade_id")
    private Integer gradeId;

    @Column(name = "sala_id")
    private String salaId;

    public Aula(){}

    public Aula(Integer aulaId, OffsetDateTime aulaAbertura, OffsetDateTime aulaFechamento, Integer gradeId, String salaId) {
        this.aulaId = aulaId;
        this.aulaAbertura = aulaAbertura;
        this.aulaFechamento = aulaFechamento;
        this.gradeId = gradeId;
        this.salaId = salaId;
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
