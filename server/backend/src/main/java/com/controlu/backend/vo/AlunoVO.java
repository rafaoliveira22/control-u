package com.controlu.backend.vo;

import org.springframework.hateoas.RepresentationModel;

public class AlunoVO extends RepresentationModel<AlunoVO> {
    private AlunoIdVO id;
    private String alunoRa;

    public AlunoVO() {}

    public AlunoVO(AlunoIdVO id, String alunoRa) {
        this.id = id;
        this.alunoRa = alunoRa;
    }

    public AlunoIdVO getId() {
        return id;
    }

    public void setId(AlunoIdVO id) {
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
        return "AlunoVO{" +
                "id=" + id +
                ", alunoRa='" + alunoRa + '\'' +
                '}';
    }
}
