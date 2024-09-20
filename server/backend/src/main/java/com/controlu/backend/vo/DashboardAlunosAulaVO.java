package com.controlu.backend.vo;

import org.springframework.hateoas.RepresentationModel;

public class DashboardAlunosAulaVO extends RepresentationModel<DashboardAlunosAulaVO> {
    private Integer quantidadeAlunosEmAula;
    private Integer quantidadeAlunosForaDeAula;

    public DashboardAlunosAulaVO(){}

    public DashboardAlunosAulaVO(Integer quantidadeAlunosEmAula, Integer quantidadeAlunosForaDeAula){
        this.quantidadeAlunosEmAula = quantidadeAlunosEmAula;
        this.quantidadeAlunosForaDeAula = quantidadeAlunosForaDeAula;
    }

    public Integer getQuantidadeAlunosEmAula() {
        return quantidadeAlunosEmAula;
    }

    public void setQuantidadeAlunosEmAula(Integer quantidadeAlunosEmAula) {
        this.quantidadeAlunosEmAula = quantidadeAlunosEmAula;
    }

    public Integer getQuantidadeAlunosForaDeAula() {
        return quantidadeAlunosForaDeAula;
    }

    public void setQuantidadeAlunosForaDeAula(Integer quantidadeAlunosForaDeAula) {
        this.quantidadeAlunosForaDeAula = quantidadeAlunosForaDeAula;
    }

    @Override
    public String toString() {
        return "DashboardAlunosAulaVO{" +
                "quantidadeAlunosEmAula=" + quantidadeAlunosEmAula +
                ", quantidadeAlunosForaDeAula=" + quantidadeAlunosForaDeAula +
                '}';
    }
}
