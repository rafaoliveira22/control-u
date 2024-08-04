package com.controlu.backend.vo;

import org.springframework.hateoas.RepresentationModel;

public class AlunoVO extends RepresentationModel<AlunoVO> {
    private String id;
    private String nome;

    public AlunoVO() {}

    public AlunoVO(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "AlunoVO{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
