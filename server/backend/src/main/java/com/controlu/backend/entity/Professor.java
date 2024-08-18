package com.controlu.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "professor")
public class Professor {
    @Id
    @Column(name = "professor_id")
    private String professorId;

    @Column(name = "professor_nome")
    private String professorNome;

    public Professor(){}

    public Professor(String professorId, String professorNome) {
        this.professorId = professorId;
        this.professorNome = professorNome;
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public String getProfessorNome() {
        return professorNome;
    }

    public void setProfessorNome(String professorNome) {
        this.professorNome = professorNome;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "professorId='" + professorId + '\'' +
                ", professorNome='" + professorNome + '\'' +
                '}';
    }
}
