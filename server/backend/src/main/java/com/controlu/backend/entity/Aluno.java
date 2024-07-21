package com.controlu.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="aluno")
public class Aluno {
    @Id
    @Column(name = "aluno_id")
    private String id;

    @Column(name = "aluno_nome")
    private String nome;

    public Aluno(){}

    public Aluno(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
