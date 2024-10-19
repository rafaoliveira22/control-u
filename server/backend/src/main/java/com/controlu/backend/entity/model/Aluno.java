package com.controlu.backend.entity.model;

import jakarta.persistence.*;

import java.util.Arrays;

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

    @Lob
    @Column(name = "aluno_face")
    private byte[] alunoFace;


    public Aluno() {
    }

    public Aluno(String alunoRa, String alunoNome, Integer cursoId) {
        this.alunoRa = alunoRa;
        this.alunoNome = alunoNome;
        this.cursoId = cursoId;
    }

    public Aluno(String alunoRa, String alunoNome, Integer cursoId, byte[] alunoFace) {
        this.alunoRa = alunoRa;
        this.alunoNome = alunoNome;
        this.cursoId = cursoId;
        this.alunoFace = alunoFace;
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

    public byte[] getAlunoFace() {
        return alunoFace;
    }

    public void setAlunoFace(byte[] alunoFace) {
        this.alunoFace = alunoFace;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "alunoRa='" + alunoRa + '\'' +
                ", alunoNome='" + alunoNome + '\'' +
                ", cursoId=" + cursoId +
                ", alunoFace=" + Arrays.toString(alunoFace) +
                '}';
    }
}
