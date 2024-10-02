package com.controlu.backend.entity.model;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "presenca")
public class Presenca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "presenca_id")
    private Integer presencaId;

    @Column(name = "presenca_entrada")
    private OffsetDateTime presencaEntrada;

    @Column(name = "presenca_saida")
    private OffsetDateTime presencaSaida;

    @Column(name = "aluno_id")
    private String alunoId;

    @Column(name = "aula_id")
    private Integer aulaId;

    public Presenca(){}

    public Presenca(Integer presencaId, OffsetDateTime presencaEntrada, OffsetDateTime presencaSaida, String alunoId, Integer aulaId) {
        this.presencaId = presencaId;
        this.presencaEntrada = presencaEntrada;
        this.presencaSaida = presencaSaida;
        this.alunoId = alunoId;
        this.aulaId = aulaId;
    }

    public Integer getPresencaId() {
        return presencaId;
    }

    public void setPresencaId(Integer presencaId) {
        this.presencaId = presencaId;
    }

    public OffsetDateTime getPresencaEntrada() {
        return presencaEntrada;
    }

    public void setPresencaEntrada(OffsetDateTime presencaEntrada) {
        this.presencaEntrada = presencaEntrada;
    }

    public OffsetDateTime getPresencaSaida() {
        return presencaSaida;
    }

    public void setPresencaSaida(OffsetDateTime presencaSaida) {
        this.presencaSaida = presencaSaida;
    }

    public String getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(String alunoId) {
        this.alunoId = alunoId;
    }

    public Integer getAulaId() {
        return aulaId;
    }

    public void setAulaId(Integer aulaId) {
        this.aulaId = aulaId;
    }

    @Override
    public String toString() {
        return "Presenca{" +
                "presencaId=" + presencaId +
                ", presencaEntrada=" + presencaEntrada +
                ", presencaSaida=" + presencaSaida +
                ", alunoId='" + alunoId + '\'' +
                ", aulaId=" + aulaId +
                '}';
    }
}
