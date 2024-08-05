package com.controlu.backend.entity.embeddable;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AlunoId implements Serializable {
    @Column(name = "aluno_nome")
    private String alunoNome;

    @Column(name = "curso_id")
    private Integer cursoId;

    @Column(name = "aluno_ano_ingressao")
    private Integer alunoAnoIngressao;

    public AlunoId() {}

    public AlunoId(String alunoNome, Integer cursoId, Integer alunoAnoIngressao) {
        this.alunoNome = alunoNome;
        this.cursoId = cursoId;
        this.alunoAnoIngressao = alunoAnoIngressao;
    }

    public String getAlunoNome() {
        return alunoNome;
    }

    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public Integer getAlunoAnoIngressao() {
        return alunoAnoIngressao;
    }

    public void setAlunoAnoIngressao(Integer alunoAnoIngressao) {
        this.alunoAnoIngressao = alunoAnoIngressao;
    }

    @Override
    public String toString() {
        return "AlunoId{" +
                "alunoNome='" + alunoNome + '\'' +
                ", cursoId=" + cursoId +
                ", alunoAnoIngressao=" + alunoAnoIngressao +
                '}';
    }
}

