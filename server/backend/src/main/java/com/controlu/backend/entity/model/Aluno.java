package com.controlu.backend.entity.model;

import jakarta.persistence.*;

@Entity
@Table(name  = "aluno")
public class Aluno {
    @Id
    @Column(name = "aluno_ra")
    private String alunoRa;

    @Column(name = "aluno_nome")
    private String alunoNome;

    @Column(name = "curso_id")
    private Integer cursoId;


    public Aluno() {
    }

    public Aluno(String alunoRa, String alunoNome, Integer cursoId) {
        this.alunoRa = alunoRa;
        this.alunoNome = alunoNome;
        this.cursoId = cursoId;
    }

    public String getAlunoRa() {
        return alunoRa;
    }

    public void setAlunoRa(String alunoRa) {
        this.alunoRa = alunoRa;
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
}
