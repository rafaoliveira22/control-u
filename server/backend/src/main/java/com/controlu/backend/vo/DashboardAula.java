package com.controlu.backend.vo;

import org.springframework.hateoas.RepresentationModel;

public class DashboardAula extends RepresentationModel<DashboardAula> {
    private Integer quantidadeAulasAcontecendo;
    private Integer quantidadeAulasNaoAcontecendo;

    public DashboardAula(){}

    public DashboardAula(Integer quantidadeAulasAcontecendo, Integer quantidadeAulasNaoAcontecendo){
        this.quantidadeAulasAcontecendo = quantidadeAulasAcontecendo;
        this.quantidadeAulasNaoAcontecendo = quantidadeAulasNaoAcontecendo;
    }

    public Integer getQuantidadeAulasAcontecendo() {
        return quantidadeAulasAcontecendo;
    }

    public void setQuantidadeAulasAcontecendo(Integer quantidadeAulasAcontecendo) {
        this.quantidadeAulasAcontecendo = quantidadeAulasAcontecendo;
    }

    public Integer getQuantidadeAulasNaoAcontecendo() {
        return quantidadeAulasNaoAcontecendo;
    }

    public void setQuantidadeAulasNaoAcontecendo(Integer quantidadeAulasNaoAcontecendo) {
        this.quantidadeAulasNaoAcontecendo = quantidadeAulasNaoAcontecendo;
    }
}
