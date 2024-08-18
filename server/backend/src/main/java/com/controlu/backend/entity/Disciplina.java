package com.controlu.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table
@Entity(name = "disciplina")
public class Disciplina {
    @Id
    @Column(name = "disciplina_id")
    private String disciplinaId;

    @Column(name = "disciplina_nome")
    private String disciplinaNome;

    public Disciplina(){}

    public Disciplina(String disciplinaId, String disciplinaNome) {
        this.disciplinaId = disciplinaId;
        this.disciplinaNome = disciplinaNome;
    }

    public String getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(String disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public String getDisciplinaNome() {
        return disciplinaNome;
    }

    public void setDisciplinaNome(String disciplinaNome) {
        this.disciplinaNome = disciplinaNome;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "disciplinaId='" + disciplinaId + '\'' +
                ", disciplinaNome='" + disciplinaNome + '\'' +
                '}';
    }
}
