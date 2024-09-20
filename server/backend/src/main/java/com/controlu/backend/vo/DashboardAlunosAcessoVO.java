package com.controlu.backend.vo;

import org.springframework.hateoas.RepresentationModel;

public class DashboardAlunosAcessoVO extends RepresentationModel<DashboardAlunosAcessoVO> {
    private Integer quantidadeAlunosNaFaculdade;
    private Integer quantidadeAlunosForaDaFaculdade;

    public DashboardAlunosAcessoVO(){}

    public DashboardAlunosAcessoVO(Integer quantidadeAlunosNaFaculdade, Integer quantidadeAlunosForaDaFaculdade){
        this.quantidadeAlunosNaFaculdade = quantidadeAlunosNaFaculdade;
        this.quantidadeAlunosForaDaFaculdade = quantidadeAlunosForaDaFaculdade;
    }

    public Integer getQuantidadeAlunosNaFaculdade() {
        return quantidadeAlunosNaFaculdade;
    }

    public void setQuantidadeAlunosNaFaculdade(Integer quantidadeAlunosNaFaculdade) {
        this.quantidadeAlunosNaFaculdade = quantidadeAlunosNaFaculdade;
    }

    public Integer getQuantidadeAlunosForaDaFaculdade() {
        return quantidadeAlunosForaDaFaculdade;
    }

    public void setQuantidadeAlunosForaDaFaculdade(Integer quantidadeAlunosForaDaFaculdade) {
        this.quantidadeAlunosForaDaFaculdade = quantidadeAlunosForaDaFaculdade;
    }

    @Override
    public String toString() {
        return "DashboardAlunosAcessoVO{" +
                "quantidadeAlunosNaFaculdade=" + quantidadeAlunosNaFaculdade +
                ", quantidadeAlunosForaDaFaculdade=" + quantidadeAlunosForaDaFaculdade +
                '}';
    }
}
