package com.controlu.backend.vo;

import org.springframework.hateoas.RepresentationModel;

public class DashboardAulaVO extends RepresentationModel<DashboardAulaVO> {
    private Integer quantidadeAulasAcontecendo;
    private Integer quantidadeAulasNaoAcontecendo;

    public DashboardAulaVO(){}

    public DashboardAulaVO(Integer quantidadeAulasAcontecendo, Integer quantidadeAulasNaoAcontecendo){
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
