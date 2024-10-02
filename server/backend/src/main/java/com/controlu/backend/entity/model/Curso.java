package com.controlu.backend.entity.model;

import jakarta.persistence.*;

@Entity
@Table(name = "curso")
public class Curso {
    @Id
    @Column(name = "curso_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cursoId;


    @Column(name = "curso_nome")
    private String cursoNome;


    public Curso(){}

    public Curso(Integer cursoId, String cursoNome) {
        this.cursoId = cursoId;
        this.cursoNome = cursoNome;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public String getCursoNome() {
        return cursoNome;
    }

    public void setCursoNome(String cursoNome) {
        this.cursoNome = cursoNome;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + cursoId +
                ", nome='" + cursoNome + '\'' +
                '}';
    }
}
