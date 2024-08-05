package com.controlu.backend.entity;

import com.controlu.backend.entity.embeddable.AlunoId;
import jakarta.persistence.*;

@Entity
@Table(name  = "aluno")
public class Aluno {

    @EmbeddedId
    private AlunoId id;

    @Column(name = "aluno_ra")
    private String alunoRa;


    public Aluno() {
    }

    public Aluno(AlunoId id, String alunoRa) {
        this.id = id;
        this.alunoRa = alunoRa;
    }

    // Getters and Setters

    public AlunoId getId() {
        return id;
    }

    public void setId(AlunoId id) {
        this.id = id;
    }

    public String getAlunoRa() {
        return alunoRa;
    }

    public void setAlunoRa(String alunoRa) {
        this.alunoRa = alunoRa;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", alunoRa='" + alunoRa + '\'' +
                '}';
    }
}
